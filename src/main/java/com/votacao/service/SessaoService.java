package com.votacao.service;

import com.votacao.converter.SessaoConverter;
import com.votacao.dto.SessaoDTO;
import com.votacao.entity.SessaoEntity;
import com.votacao.repository.SessaoRepository;
import org.springframework.stereotype.Service;

@Service
public class SessaoService {

    private SessaoRepository sessaoRepository;

    private SessaoConverter sessaoConverter;

    public SessaoService(SessaoRepository sessaoRepository, SessaoConverter sessaoConverter) {
        this.sessaoRepository = sessaoRepository;
        this.sessaoConverter = sessaoConverter;
    }

    public SessaoEntity cadastrar(SessaoDTO sessaoDTO) {
        return sessaoRepository.save(sessaoConverter.convertToEntity(sessaoDTO));
    }
}
