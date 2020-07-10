package com.votacao.converter;

import com.votacao.dto.SessaoDTO;
import com.votacao.entity.SessaoEntity;
import org.springframework.stereotype.Component;

@Component
public class SessaoConverter {

    public SessaoEntity convertToEntity(SessaoDTO sessaoDTO){
        return SessaoEntity.builder()
                .dataHoraFim(sessaoDTO.getDataHoraInicio())
                .id(sessaoDTO.getId())
                .pauta(sessaoDTO.getPauta())
                .statusSessao(sessaoDTO.getStatusSessao())
                .dataHoraInicio(sessaoDTO.getDataHoraInicio())
                .build();
    }

    public SessaoDTO convertToDTO(SessaoEntity sessaoEntity){
        return  SessaoDTO.builder()
                .dataHoraInicio(sessaoEntity.getDataHoraInicio())
                .id(sessaoEntity.getId())
                .statusSessao(sessaoEntity.getStatusSessao())
                .dataHoraFim(sessaoEntity.getDataHoraFim())
                .pauta(sessaoEntity.getPauta())
                .build();
    }
}
