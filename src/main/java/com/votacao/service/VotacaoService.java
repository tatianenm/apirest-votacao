package com.votacao.service;

import com.votacao.converter.VotacaoConverter;
import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.entity.VotacaoEntity;
import com.votacao.exception.VotoException;
import com.votacao.repository.VotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class VotacaoService {

    private VotacaoRepository votacaoRepository;

    private VotacaoConverter votacaoConverter;

    private SessaoService sessaoService;

    @Autowired
    public VotacaoService(VotacaoRepository votacaoRepository, VotacaoConverter votacaoConverter, SessaoService sessaoService) {
        this.votacaoRepository = votacaoRepository;
        this.votacaoConverter = votacaoConverter;
        this.sessaoService = sessaoService;
    }


    public VotacaoEntity salvarVotacao(VotacaoInclusaoDTO votacaoInclusaoDTO) {
        if(sessaoService.verificaSessaoExpirada(votacaoInclusaoDTO.getSessao().getId())){
            throw new VotoException("Não é possível cadastrar voto, sessão expirada.");
        }

        if (validaVotoRepetido(votacaoInclusaoDTO)) {
            throw new VotoException("Voto repetido.");
        }

        return votacaoRepository.save(votacaoConverter.convertToEntity(votacaoInclusaoDTO));
    }

    private Boolean validaVotoRepetido(VotacaoInclusaoDTO votacaoDTO){
      var votacao =  votacaoConverter.convertToEntity(votacaoDTO);
        return votacaoRepository
                .existsByDataSistemaAndAssociadoAndSessao(votacaoDTO.getDataSistema(),
                         votacao.getAssociado(), votacao.getSessao());

    }


}
