package com.votacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class VotacaoListaDTO {

    private Long id;

    private String votoSim;

    private String votoNão;

    private SessaoDTO sessao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataSistema = LocalDateTime.now();

    private Long idPauta;

    private String nomePauta;
}
