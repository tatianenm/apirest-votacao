package com.votacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class VotacaoListaDTO {

    private Long id;

    private Long votoSim;

    private Long votoNão;

    private Long idSessao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataSistema = LocalDate.now();

    private Long idPauta;

    private String nomePauta;
}
