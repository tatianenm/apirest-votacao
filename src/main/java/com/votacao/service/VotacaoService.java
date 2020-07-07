package com.votacao.service;

import com.votacao.converter.VotacaoConverter;
import com.votacao.dto.PautaInclusaoDTO;
import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.repository.VotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        //validar voto não pode ser repetido
        votacaoRepository.save(votacaoConverter.convertToEntity(votacaoInclusaoDTO));
    }
}
