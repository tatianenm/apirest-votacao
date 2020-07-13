package com.votacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotacaoListaDTO {

    private Long votoSim;

    private Long votoNão;

    private Long idSessao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataSistema = LocalDate.now();

    private Long idPauta;

    private String nomePauta;
}
