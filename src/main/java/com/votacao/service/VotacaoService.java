package com.votacao.service;

import com.votacao.converter.VotacaoConverter;
import com.votacao.dto.PautaInclusaoDTO;
import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.entity.VotacaoEntity;
import com.votacao.exception.VotoException;
import com.votacao.repository.VotacaoRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.graalvm.compiler.phases.common.DeadCodeEliminationPhase.Optionality.Optional;

@Service
public class VotacaoService {

    private VotacaoRepository votacaoRepository;

    private VotacaoConverter votacaoConverter;

    @Autowired
    public VotacaoService(VotacaoRepository votacaoRepository, VotacaoConverter votacaoConverter) {
        this.votacaoRepository = votacaoRepository;
        this.votacaoConverter = votacaoConverter;
    }


    public void votar(VotacaoInclusaoDTO votacaoInclusaoDTO) {
        if (validaVotoRepetido(votacaoInclusaoDTO)) {
            throw new VotoException();
        }
        var votacao = votacaoRepository.save(votacaoConverter.convertToEntity(votacaoInclusaoDTO));
    }

    private Boolean validaVotoRepetido(VotacaoInclusaoDTO votacaoDTO){
       return  votacaoRepository
               .findByDataSistemaAndPautaDTOAndAssociadoDTO(votacaoDTO.getData(),
                       votacaoDTO.getPautaDTO(), votacaoDTO.getAssociadoDTO()) != null;
    }
}
