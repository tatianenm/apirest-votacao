package com.votacao.converter;

import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.entity.AssociadoEntity;
import com.votacao.entity.VotacaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class VotacaoConverter {

    @Autowired
    private PautaConverter pautaConverter;

    @Autowired
    private AssociadoConverter associadoConverter;

    public VotacaoEntity convertToEntity(VotacaoInclusaoDTO votacaoInclusaoDTO){
        return VotacaoEntity.builder()
                .id(votacaoInclusaoDTO.getId())
                .pautaEntity(pautaConverter.convertToEntity(votacaoInclusaoDTO.getPautaDTO()))
                .associados(Arrays
                        .asList(associadoConverter.convertToEntity(votacaoInclusaoDTO.getAssociadoDTO())))
                .voto(votacaoInclusaoDTO.getVoto())
                .build();
    }

    public VotacaoInclusaoDTO convertToDTO(VotacaoEntity votacaoEntity){
        return VotacaoInclusaoDTO.builder()
                .id(votacaoEntity.getId())
                .voto(votacaoEntity.getVoto())
                .associadoDTO(associadoConverter.convertToAssociadoDTO(votacaoEntity.getAssociados().get(0)))
                .pautaDTO(pautaConverter.convertToPautaDTO(votacaoEntity.getPautaEntity()))
                .build();
    }
}
