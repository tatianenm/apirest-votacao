package com.votacao.converter;

import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.entity.VotacaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class VotacaoConverter {

    private PautaConverter pautaConverter;

    private AssociadoConverter associadoConverter;

    @Autowired
    public VotacaoConverter(PautaConverter pautaConverter, AssociadoConverter associadoConverter) {
        this.pautaConverter = pautaConverter;
        this.associadoConverter = associadoConverter;
    }

    public VotacaoEntity convertToEntity(VotacaoInclusaoDTO votacaoInclusaoDTO){
        return VotacaoEntity.builder()
                .id(votacaoInclusaoDTO.getId())
                .pautaEntity(pautaConverter.convertToEntity(votacaoInclusaoDTO.getPautaDTO()))
//                .associados(votacaoInclusaoDTO.getAssociados().forEach(a->{
//                    associadoConverter.convertToEntity(a);
//                }))
                .voto(votacaoInclusaoDTO.getVoto())
                .build();
    }

    public VotacaoInclusaoDTO convertToDTO(VotacaoEntity votacaoEntity){
        return VotacaoInclusaoDTO.builder()
                .id(votacaoEntity.getId())
                .voto(votacaoEntity.getVoto())
                //.associadoDTO(associadoConverter.convertToDTO(votacaoEntity.getAssociadoEntity()))
                .pautaDTO(pautaConverter.convertToDTO(votacaoEntity.getPautaEntity()))
                .build();
    }
}
