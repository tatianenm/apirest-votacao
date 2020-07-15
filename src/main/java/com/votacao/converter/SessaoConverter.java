package com.votacao.converter;

import com.votacao.dto.SessaoDTO;
import com.votacao.dto.SessaoInclusaoDTO;
import com.votacao.dto.SessaoListaDTO;
import com.votacao.entity.SessaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessaoConverter {
    @Autowired
    private PautaConverter pautaConverter;

    public SessaoEntity convertToEntity(SessaoInclusaoDTO sessaoDTO) {
        return SessaoEntity.builder()
                .id(sessaoDTO.getId())
                .validade(sessaoDTO.getValidadeMinutos())
                .build();
    }

    public SessaoInclusaoDTO convertToDTO(SessaoEntity sessaoEntity) {
        return SessaoInclusaoDTO.builder()
                .id(sessaoEntity.getId())
                .dataHoraInicio(sessaoEntity.getDataHoraInicio())
                .validadeMinutos(sessaoEntity.getValidade())
                .pauta(pautaConverter.convertToPautaDTO(sessaoEntity.getPauta()))
                .statusSessao(sessaoEntity.getStatusSessao())
                .build();
    }

    public SessaoEntity convertToEntity(SessaoDTO sessaoDTO) {
        return SessaoEntity.builder()
                .id(sessaoDTO.getId())
                .build();
    }

    public SessaoDTO convertToSessaoDTO(SessaoEntity sessaoEntity) {
        return SessaoDTO.builder()
                .id(sessaoEntity.getId())
                .build();
    }
    public SessaoListaDTO convertToListaDTO(SessaoEntity sessaoEntity) {
        return SessaoListaDTO.builder()
                .id(sessaoEntity.getId())
                .validadeMinutos(sessaoEntity.getValidade())
                .dataHoraInicio(sessaoEntity.getDataHoraInicio())
                .pauta(pautaConverter.convertToPautaDTO(sessaoEntity.getPauta()))
                .statusSessao(sessaoEntity.getStatusSessao())
                .build();
    }
}
