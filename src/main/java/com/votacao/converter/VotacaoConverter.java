package com.votacao.converter;

import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.dto.VotacaoListaDTO;
import com.votacao.entity.VotacaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VotacaoConverter {

    @Autowired
    private PautaConverter pautaConverter;

    @Autowired
    private AssociadoConverter associadoConverter;

    @Autowired
    private SessaoConverter sessaoConverter;

    public VotacaoEntity convertToEntity(VotacaoInclusaoDTO votacaoInclusaoDTO) {
        return VotacaoEntity.builder()
                .id(votacaoInclusaoDTO.getId())
                .associado(associadoConverter.convertToEntity(votacaoInclusaoDTO.getAssociado()))
                .voto(votacaoInclusaoDTO.getVoto())
                .sessao(sessaoConverter.convertToEntity(votacaoInclusaoDTO.getSessao()))
                .dataSistema(votacaoInclusaoDTO.getDataSistema())
                .build();
    }

    public VotacaoInclusaoDTO convertToDTO(VotacaoEntity votacaoEntity) {
        return VotacaoInclusaoDTO.builder()
                .id(votacaoEntity.getId())
                .voto(votacaoEntity.getVoto())
                .associado(associadoConverter.convertToAssociadoDTO(votacaoEntity.getAssociado()))
                .sessao(sessaoConverter.convertToSessaoDTO(votacaoEntity.getSessao()))
                .dataSistema(votacaoEntity.getDataSistema())
                .build();
    }

    public List<VotacaoListaDTO> convertToDTO(List<VotacaoEntity> votacoes) {
        var dtos = new ArrayList<VotacaoListaDTO>();
        votacoes
                .forEach(v -> {
                    dtos.add(VotacaoListaDTO.builder()
                            .dataSistema(v.getDataSistema())
                            .idSessao(v.getSessao().getId())
                            .nomePauta(v.getSessao().getPauta().getNomePauta())
                            .idPauta(v.getSessao().getPauta().getId())
                            .build());
                });

        return dtos;
    }

}
