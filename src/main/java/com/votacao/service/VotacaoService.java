package com.votacao.service;

import com.votacao.converter.PautaConverter;
import com.votacao.converter.VotacaoConverter;
import com.votacao.domain.VotoEnum;
import com.votacao.dto.PautaDTO;
import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.entity.VotacaoEntity;
import com.votacao.exception.PautaNotFoundException;
import com.votacao.exception.VotoException;
import com.votacao.repository.VotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotacaoService {

    private VotacaoRepository votacaoRepository;

    private VotacaoConverter votacaoConverter;

    private SessaoService sessaoService;

    private PautaConverter pautaConverter;

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
                .existsByDataSistemaAndAssociadoAndSessao(votacaoDTO.getDataSistema(),
                        votacao.getAssociado(), votacao.getSessao());

    }


    public List<VotacaoInclusaoDTO> listarVotações(PautaDTO pautaDTO) {
        var votacoes = votacaoRepository.findBySessao_Pauta(pautaConverter.convertToEntity(pautaDTO));
        if (votacoes.isEmpty()) {
            throw new PautaNotFoundException(
                    String.format("Nenhuma votação foi encontrada para pauta id %s",
                            pautaDTO.getId()));
        }
        votacoes.stream()
                .map(votacaoConverter:: convertToListaDTO);


        Long sim = getCount(votacoes, VotoEnum.SIM);
        Long nao = getCount(votacoes, VotoEnum.NAO);
        return null;
    }

    private long getCount(List<VotacaoEntity> votacoes, VotoEnum votoEnum) {
        return votacoes.stream()
                .filter(v -> v.getVoto().equals(votoEnum))
                .count();
    }
}
