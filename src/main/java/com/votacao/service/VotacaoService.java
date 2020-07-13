package com.votacao.service;

import com.votacao.converter.PautaConverter;
import com.votacao.converter.VotacaoConverter;
import com.votacao.domain.VotoEnum;
import com.votacao.dto.PautaDTO;
import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.dto.VotacaoListaDTO;
import com.votacao.entity.VotacaoEntity;
import com.votacao.exception.PautaNotFoundException;
import com.votacao.exception.VotoException;
import com.votacao.repository.VotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class VotacaoService {

    private static final String ENDPOINT_VALIDA_CPF = "https://user-info.herokuapp.com/users/";

    private VotacaoRepository votacaoRepository;

    private VotacaoConverter votacaoConverter;

    private SessaoService sessaoService;

    private PautaConverter pautaConverter;

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    public VotacaoService(VotacaoRepository votacaoRepository, VotacaoConverter votacaoConverter,
                          SessaoService sessaoService, PautaConverter pautaConverter) {
        this.votacaoRepository = votacaoRepository;
        this.votacaoConverter = votacaoConverter;
        this.sessaoService = sessaoService;
        this.pautaConverter = pautaConverter;

    }


    public VotacaoEntity salvarVotacao(VotacaoInclusaoDTO votacaoInclusaoDTO) {
        if (sessaoService.verificaSessaoExpirada(votacaoInclusaoDTO.getSessao().getId())) {
            throw new VotoException("Não é possível cadastrar voto, sessão expirada.");
        }

        if (validaVotoRepetido(votacaoInclusaoDTO)) {
            throw new VotoException("Voto repetido.");
        }

        return votacaoRepository.save(votacaoConverter.convertToEntity(votacaoInclusaoDTO));
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
        var dtos = votacaoConverter.convertToDTO(votacoes)
                .stream()
                .collect(Collectors.groupingBy(dto -> dto.getIdSessao()))
                .values()
                .stream()
                .map(v -> v.stream().findFirst().get())
                .collect(toList());
        var votacoesDto = new ArrayList<VotacaoListaDTO>();
        dtos.forEach(vDTO -> {
            vDTO.setVotoSim(getCount(vDTO.getIdSessao(), VotoEnum.SIM));
            vDTO.setVotoNão(getCount(vDTO.getIdSessao(), VotoEnum.NAO));
            votacoesDto.add(vDTO);
        });
        return votacoesDto;

    }

    public HttpStatus validarCpf(String cpf) {
        return restTemplate
                .getForEntity(ENDPOINT_VALIDA_CPF + cpf, String.class)
                .getStatusCode();
    }

    private Long getCount(Long idSessao, VotoEnum voto) {
        return votacaoRepository.countBySessao_IdAndVoto(idSessao, voto);
    }
}
