package com.votacao.converter;

import com.votacao.dto.PautaDTO;
import com.votacao.dto.PautaInclusaoDTO;
import com.votacao.dto.PautaListaDTO;
import com.votacao.entity.PautaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PautaConverter {

    @Autowired
    private VotacaoConverter votacaoConverter;

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

    public PautaListaDTO convertToListaDTO(PautaEntity pautaEntity) {
        return PautaListaDTO.builder()
                .id(pautaEntity.getId())
                .nomePauta(pautaEntity.getNomePauta())
                .build();
    }
    public PautaEntity convertToEntity(PautaDTO pautaDTO) {
        return PautaEntity.builder()
                .id(pautaDTO.getId())
                .build();
    }
}
