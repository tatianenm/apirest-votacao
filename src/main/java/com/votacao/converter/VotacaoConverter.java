package com.votacao.converter;

import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.dto.VotacaoListaDTO;
import com.votacao.entity.VotacaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VotacaoConverter {

    @Autowired
    private PautaConverter pautaConverter;

    @Autowired
    private AssociadoConverter associadoConverter;

    @Autowired
    private SessaoConverter sessaoConverter;

    public VotacaoEntity convertToEntity(VotacaoInclusaoDTO votacaoInclusaoDTO){
        return VotacaoEntity.builder()
                .id(votacaoInclusaoDTO.getId())
                .associado(associadoConverter.convertToEntity(votacaoInclusaoDTO.getAssociado()))
                .voto(votacaoInclusaoDTO.getVoto())
                .sessao(sessaoConverter.convertToEntity(votacaoInclusaoDTO.getSessao()))
                .dataSistema(votacaoInclusaoDTO.getDataSistema())
                .build();
    }

    public VotacaoInclusaoDTO convertToDTO(VotacaoEntity votacaoEntity){
        return VotacaoInclusaoDTO.builder()
                .id(votacaoEntity.getId())
                .voto(votacaoEntity.getVoto())
                .associado(associadoConverter.convertToAssociadoDTO(votacaoEntity.getAssociado()))
                .sessao(sessaoConverter.convertToDTO(votacaoEntity.getSessao()))
                .dataSistema(votacaoEntity.getDataSistema())
                .build();
    }

    public VotacaoListaDTO convertToListaDTO(VotacaoEntity votacaoEntity){
        return VotacaoListaDTO.builder()
                .dataSistema(votacaoEntity.getDataSistema())
                .id(votacaoEntity.getId())
                .sessao(sessaoConverter.convertToDTO(votacaoEntity.getSessao()))
                .idPauta(votacaoEntity.getSessao().getPauta().getId())
                .nomePauta(votacaoEntity.getSessao().getPauta().getNomePauta())
                .votoNão("")
                .votoSim("")
                .build();
    }
}
