package com.votacao.service;

import com.votacao.converter.AssociadoConverter;
import com.votacao.dto.AssociadoInclusaoDTO;
import com.votacao.entity.AssociadoEntity;
import com.votacao.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {

    private AssociadoRepository associadoRepository;

    private AssociadoConverter associadoConverter;

    @Autowired
    public AssociadoService(AssociadoRepository associadoRepository, AssociadoConverter associadoConverter) {
        this.associadoRepository = associadoRepository;
        this.associadoConverter = associadoConverter;
    }

    public AssociadoEntity cadastrar(AssociadoInclusaoDTO associadoInclusaoDTO) {
        return associadoRepository.save(associadoConverter.convertToEntity(associadoInclusaoDTO));
    }
}
