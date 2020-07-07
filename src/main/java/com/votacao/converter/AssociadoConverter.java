package com.votacao.converter;

import com.votacao.dto.AssociadoInclusaoDTO;
import com.votacao.entity.AssociadoEntity;
import org.springframework.stereotype.Component;

@Component
public class AssociadoConverter {

    public AssociadoEntity convertToEntity(AssociadoInclusaoDTO associadoInclusaoDTO){
        return AssociadoEntity.builder()
                .id(associadoInclusaoDTO.getId())
                .nome(associadoInclusaoDTO.getNome())
                .build();
    }

    public AssociadoInclusaoDTO convertToDTO(AssociadoEntity associadoEntity){
        return AssociadoInclusaoDTO.builder()
                .id(associadoEntity.getId())
                .nome(associadoEntity.getNome())
                .build();
    }
}
