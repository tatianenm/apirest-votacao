package com.votacao.converter;

import com.votacao.dto.SessaoDTO;
import com.votacao.entity.SessaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessaoConverter {
    @Autowired
    private PautaConverter pautaConverter;

    public SessaoEntity convertToEntity(SessaoDTO sessaoDTO){
        return SessaoEntity.builder()
                .id(sessaoDTO.getId())
                .build();
    }

    public SessaoDTO convertToDTO(SessaoEntity sessaoEntity){
        return  SessaoDTO.builder()
                .id(sessaoEntity.getId())
                .build();
    }
}
