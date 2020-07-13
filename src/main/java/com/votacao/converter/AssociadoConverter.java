package com.votacao.converter;

import com.votacao.dto.AssociadoDTO;
import com.votacao.dto.AssociadoInclusaoDTO;
import com.votacao.dto.AssociadoListaDTO;
import com.votacao.entity.AssociadoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssociadoConverter {

    @Autowired
    private VotacaoConverter votacaoConverter;

    public AssociadoEntity convertToEntity(AssociadoInclusaoDTO associadoInclusaoDTO) {
        return AssociadoEntity.builder()
                .id(associadoInclusaoDTO.getId())
                .nome(associadoInclusaoDTO.getNome())
                .cpf(associadoInclusaoDTO.getCpf())
                .build();
    }

    public AssociadoInclusaoDTO convertToDTO(AssociadoEntity associadoEntity) {
        return AssociadoInclusaoDTO.builder()
                .id(associadoEntity.getId())
                .nome(associadoEntity.getNome())
                .cpf(associadoEntity.getCpf())
                .build();
    }

    public AssociadoEntity convertToEntity(AssociadoDTO associadoDTO) {
        return AssociadoEntity.builder()
                .id(associadoDTO.getId())
                .build();
    }

    public AssociadoDTO convertToAssociadoDTO(AssociadoEntity associadoEntity) {
        return AssociadoDTO.builder()
                .id(associadoEntity.getId())
                .build();
    }

    public AssociadoListaDTO convertToListaDTO(AssociadoEntity associadoEntity) {
        return AssociadoListaDTO.builder()
                .id(associadoEntity.getId())
                .nome(associadoEntity.getNome())
                .cpf(associadoEntity.getCpf())
                .build();
    }


}
