package com.votacao.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
public class AssociadoListaDTO {

    private Long id;

    private String nome;

    private String cpf;

}
