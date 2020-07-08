package com.votacao.converter;

import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.entity.VotacaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VotacaoConverter {

    @Autowired
    private PautaConverter pautaConverter;

    public VotacaoEntity convertToEntity(VotacaoInclusaoDTO votacaoInclusaoDTO){
        return VotacaoEntity.builder()
                .id(votacaoInclusaoDTO.getId())
                .pautaEntity(pautaConverter.convertToEntity(votacaoInclusaoDTO.getPautaDTO()))
                .voto(votacaoInclusaoDTO.getVoto())
                .build();
    }

    public VotacaoInclusaoDTO convertToDTO(VotacaoEntity votacaoEntity){
        return VotacaoInclusaoDTO.builder()
                .id(votacaoEntity.getId())
                .voto(votacaoEntity.getVoto())
                .pautaDTO(pautaConverter.convertToDTO(votacaoEntity.getPautaEntity()))
                .build();
    }
}
