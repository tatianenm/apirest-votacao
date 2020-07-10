package com.votacao.converter;

import com.votacao.dto.VotacaoInclusaoDTO;
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
                .pauta(pautaConverter.convertToEntity(votacaoInclusaoDTO.getPauta()))
                .associados(Arrays
                        .asList(associadoConverter.convertToEntity(votacaoInclusaoDTO.getAssociado())))
                .voto(votacaoInclusaoDTO.getVoto())
                .dataSistema(votacaoInclusaoDTO.getDataSistema())
                .build();
    }

    public VotacaoInclusaoDTO convertToDTO(VotacaoEntity votacaoEntity){
        return VotacaoInclusaoDTO.builder()
                .id(votacaoEntity.getId())
                .voto(votacaoEntity.getVoto())
                .associado(associadoConverter.convertToAssociadoDTO(votacaoEntity.getAssociados().get(0)))
                .pauta(pautaConverter.convertToPautaDTO(votacaoEntity.getPauta()))
                .dataSistema(votacaoEntity.getDataSistema())
                .build();
    }
}
