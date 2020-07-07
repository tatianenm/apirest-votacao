package com.votacao.service;

import com.votacao.converter.PautaConverter;
import com.votacao.dto.PautaInclusaoDTO;
import com.votacao.entity.PautaEntity;
import com.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    private PautaRepository pautaRepository;

    private PautaConverter pautaConverter;

    @Autowired
    public PautaService(PautaRepository pautaRepository, PautaConverter pautaConverter) {
        this.pautaRepository = pautaRepository;
        this.pautaConverter = pautaConverter;
    }

    public PautaEntity cadastrar(PautaInclusaoDTO pautaInclusaoDTO) {
       return pautaRepository.save(pautaConverter.convertToEntity(pautaInclusaoDTO));
    }
}
