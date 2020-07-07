package com.votacao.converter;

import com.votacao.dto.PautaInclusaoDTO;
import com.votacao.entity.PautaEntity;
import org.springframework.stereotype.Component;

@Component
public class PautaConverter {

    public PautaEntity convertToEntity(PautaInclusaoDTO pautaDTO) {
        return PautaEntity.builder()
                .id(pautaDTO.getId())
                .nomePauta(pautaDTO.getNomePauta())
                .build();
    }

    public PautaInclusaoDTO convertToDTO(PautaEntity pautaEntity) {
        return PautaInclusaoDTO.builder()
                .id(pautaEntity.getId())
                .nomePauta(pautaEntity.getNomePauta())
                .build();
    }
}
