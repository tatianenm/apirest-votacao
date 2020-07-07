package com.votacao.controller;

import com.votacao.converter.VotacaoConverter;
import com.votacao.dto.PautaInclusaoDTO;
import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.service.VotacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(value = "api-rest/v1/votacao")
public class VotacaoController {

    private VotacaoService votacaoService;

    private VotacaoConverter votacaoConverter;

    @Autowired
    public VotacaoController(VotacaoService votacaoService, VotacaoConverter votacaoConverter) {
        this.votacaoService = votacaoService;
        this.votacaoConverter = votacaoConverter;
    }

    @ApiOperation(value = "Votação de pauta")
    @GetMapping()
    public ResponseEntity votar(@RequestBody @Valid VotacaoInclusaoDTO votacaoInclusaoDTO){
        votacaoService.votar(votacaoInclusaoDTO);
return null;
    }


}
