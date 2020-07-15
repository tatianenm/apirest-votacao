package com.votacao.service;

import com.votacao.converter.VotacaoConverter;
import com.votacao.domain.VotoEnum;
import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.dto.VotacaoListaDTO;
import com.votacao.entity.AssociadoEntity;
import com.votacao.entity.VotacaoEntity;
import com.votacao.exception.CpfException;
import com.votacao.exception.PautaNotFoundException;
import com.votacao.exception.VotoException;
import com.votacao.repository.VotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class VotacaoService {

    private static final String ENDPOINT_VALIDA_CPF = "https://user-info.herokuapp.com/users/";

    private VotacaoRepository votacaoRepository;

    private VotacaoConverter votacaoConverter;

    private SessaoService sessaoService;

    private AssociadoService associadoService;


    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    public VotacaoService(VotacaoRepository votacaoRepository, VotacaoConverter votacaoConverter,
                          SessaoService sessaoService, AssociadoService associadoService) {
        this.votacaoRepository = votacaoRepository;
        this.votacaoConverter = votacaoConverter;
        this.sessaoService = sessaoService;
        this.associadoService = associadoService;

    }

    public VotacaoEntity salvarVotacao(VotacaoInclusaoDTO votacaoInclusaoDTO) {
        if (sessaoService.verificaSessaoExpirada(votacaoInclusaoDTO.getSessao().getId())) {
            throw new VotoException("Não é possível cadastrar voto, sessão expirada.");
        }

        if (validaVotoRepetido(votacaoInclusaoDTO)) {
            throw new VotoException("Voto repetido.");
        }

        if (Objects.isNull(isCpfAbleToVote(retornaCpfAssociado(votacaoInclusaoDTO)))) {
            throw new CpfException("Cpf inválido.");
        }

        return votacaoRepository.save(votacaoConverter.convertToEntity(votacaoInclusaoDTO));
    }

    private String retornaCpfAssociado(VotacaoInclusaoDTO votacaoInclusaoDTO) {
        return associadoService.retornaAssociadoPeloId(votacaoInclusaoDTO.getAssociado().getId())
                .orElse(new AssociadoEntity()).getCpf();
    }

    private Boolean validaVotoRepetido(VotacaoInclusaoDTO votacaoDTO) {
        var votacao = votacaoConverter.convertToEntity(votacaoDTO);
        return votacaoRepository
                .existsByDataSistemaAndAssociado_IdAndSessao_Id(votacaoDTO.getDataSistema(),
                        votacao.getAssociado().getId(), votacao.getSessao().getId());

    }

    public List<VotacaoListaDTO> listarVotacao(Long idPauta) {
        var votacoes = votacaoRepository.findBySessao_Pauta_id(idPauta);
        if (votacoes.isEmpty()) {
            throw new PautaNotFoundException(
                    String.format("Nenhuma votação foi encontrada para pauta id %s",
                            idPauta));
        }
        var dtos = retornaVotacaoDTOsDistinctPeloIdSessao(votacoes);

        var votacoesDto = new ArrayList<VotacaoListaDTO>();

        dtos.forEach(vDTO -> {
            vDTO.setVotoSim(getCount(vDTO.getIdSessao(), VotoEnum.SIM));
            vDTO.setVotoNão(getCount(vDTO.getIdSessao(), VotoEnum.NAO));
            votacoesDto.add(vDTO);
        });
        return votacoesDto;

    }

    private List<VotacaoListaDTO> retornaVotacaoDTOsDistinctPeloIdSessao(List<VotacaoEntity> votacoes) {
        return votacaoConverter.convertToDTO(votacoes)
                .stream()
                .collect(Collectors.groupingBy(dto -> dto.getIdSessao()))
                .values()
                .stream()
                .map(v -> v.stream().findFirst().get())
                .collect(toList());
    }

    private Boolean isCpfAbleToVote(String cpf) {
        var status = restTemplate
                .getForEntity(ENDPOINT_VALIDA_CPF + cpf, Map.class)
                .getBody()
                .get("status");

        if (!Objects.equals("ABLE_TO_VOTE", status)) {
            throw new CpfException("UNABLE_TO_VOTE");
        }
        return true;
    }

    private Long getCount(Long idSessao, VotoEnum voto) {
        return votacaoRepository.countBySessao_IdAndVoto(idSessao, voto);
    }
}
