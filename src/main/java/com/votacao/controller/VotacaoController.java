package com.votacao.controller;

import com.votacao.converter.VotacaoConverter;
import com.votacao.dto.*;
import com.votacao.entity.VotacaoEntity;
import com.votacao.service.VotacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Api(value = "api-rest/v1/votacao")
@RequestMapping("api-rest/v1/votacao")
public class VotacaoController {

    private VotacaoService votacaoService;

    private VotacaoConverter votacaoConverter;

    @Autowired
    public VotacaoController(VotacaoService votacaoService, VotacaoConverter votacaoConverter) {
        this.votacaoService = votacaoService;
        this.votacaoConverter = votacaoConverter;
    }

    @ApiOperation(value = "Votação de pauta")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VotacaoInclusaoDTO> cadastrar(
            @RequestBody @Valid VotacaoInclusaoDTO votacaoInclusaoDTO, UriComponentsBuilder uriBuilder) {
        VotacaoEntity votacaoEntity = votacaoService.salvarVotacao(votacaoInclusaoDTO);
        URI uri = uriBuilder.path("/votacao/{id}").buildAndExpand(votacaoEntity.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(votacaoConverter.convertToDTO(votacaoEntity));
    }

    @ApiOperation(value = "Lista de votações")
    @GetMapping
    public List<VotacaoListaDTO> listarVotações(
            @RequestParam(value = "idPauta", required = true) Long idPauta) {
        return votacaoService.listarVotacao(idPauta);
    }

    @ApiOperation(value = "Validar CPF")
    @GetMapping("/{cpf}")
    public ResponseEntity validaCpf(@RequestParam(value = "cpf", required = true) String cpf) {
        if (!votacaoService.validarCpf(cpf).is2xxSuccessful()) {
            return new ResponseEntity<>(
                    "UNABLE_TO_VOTE",
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                "ABLE_TO_VOTE)",
                HttpStatus.OK);
    }

}
