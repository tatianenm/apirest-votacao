package com.votacao.converter;

import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.entity.VotacaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VotacaoConverter {

    @Autowired
    private PautaConverter pautaConverter;

    @Autowired
    private AssociadoConverter associadoConverter;

    public VotacaoEntity convertToEntity(VotacaoInclusaoDTO votacaoInclusaoDTO){
        return VotacaoEntity.builder()
                .id(votacaoInclusaoDTO.getId())
                .associado(associadoConverter.convertToEntity(votacaoInclusaoDTO.getAssociado()))
                .voto(votacaoInclusaoDTO.getVoto())
                .dataSistema(votacaoInclusaoDTO.getDataSistema())
                .build();
    }

    public VotacaoInclusaoDTO convertToDTO(VotacaoEntity votacaoEntity){
        return VotacaoInclusaoDTO.builder()
                .id(votacaoEntity.getId())
                .voto(votacaoEntity.getVoto())
                .associado(associadoConverter.convertToAssociadoDTO(votacaoEntity.getAssociado()))
                .dataSistema(votacaoEntity.getDataSistema())
                .build();
    }
}
