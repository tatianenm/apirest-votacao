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

    @Autowired
    public VotacaoService(VotacaoRepository votacaoRepository, VotacaoConverter votacaoConverter) {
        this.votacaoRepository = votacaoRepository;
        this.votacaoConverter = votacaoConverter;
    }


    public VotacaoEntity salvarVotacao(VotacaoInclusaoDTO votacaoInclusaoDTO) {
        if (validaVotoRepetido(votacaoInclusaoDTO)) {
            throw new VotoException();
        }

        return votacaoRepository.save(votacaoConverter.convertToEntity(votacaoInclusaoDTO));


    }

    private Boolean validaVotoRepetido(VotacaoInclusaoDTO votacaoDTO){
        return votacaoRepository
                .findByDataSistemaAndPautaEntity_idAndAssociados_Id(votacaoDTO.getDataSistema(),
                        votacaoDTO.getPautaDTO().getId(), votacaoDTO.getAssociadoDTO().getId()) != null ;

    }


}
