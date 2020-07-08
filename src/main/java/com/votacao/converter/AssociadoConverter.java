package com.votacao.converter;

import com.votacao.dto.AssociadoInclusaoDTO;
import com.votacao.entity.AssociadoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssociadoConverter {

    @Autowired
    private VotacaoConverter votacaoConverter;

    public AssociadoEntity convertToEntity(AssociadoInclusaoDTO associadoInclusaoDTO){
        return AssociadoEntity.builder()
                .id(associadoInclusaoDTO.getId())
                .nome(associadoInclusaoDTO.getNome())
                .cpf(associadoInclusaoDTO.getCpf())
                .build();
    }

    public AssociadoInclusaoDTO convertToDTO(AssociadoEntity associadoEntity){
        return AssociadoInclusaoDTO.builder()
                .id(associadoEntity.getId())
                .nome(associadoEntity.getNome())
                .cpf(associadoEntity.getCpf())
                .build();
    }
}
