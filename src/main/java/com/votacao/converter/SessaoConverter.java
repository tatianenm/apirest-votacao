package com.votacao.converter;

import com.votacao.dto.SessaoDTO;
import com.votacao.dto.SessaoInclusaoDTO;
import com.votacao.entity.SessaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessaoConverter {
    @Autowired
    private PautaConverter pautaConverter;

    public SessaoEntity convertToEntity(SessaoInclusaoDTO sessaoDTO) {
        return SessaoEntity.builder()
                .id(sessaoDTO.getId())
                .validade(sessaoDTO.getValidade())
                .build();
    }

    public SessaoInclusaoDTO convertToDTO(SessaoEntity sessaoEntity) {
        return SessaoInclusaoDTO.builder()
                .id(sessaoEntity.getId())
                .validade(sessaoEntity.getValidade())
                .build();
    }

    public SessaoEntity convertToEntity(SessaoDTO sessaoDTO) {
        return SessaoEntity.builder()
                .id(sessaoDTO.getId())
                .build();
    }

    public SessaoDTO convertToSessaoDTO(SessaoEntity sessaoEntity) {
        return SessaoDTO.builder()
                .id(sessaoEntity.getId())
                .build();
    }
}
