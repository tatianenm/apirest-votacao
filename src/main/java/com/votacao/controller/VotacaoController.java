package com.votacao.controller;

import com.votacao.converter.VotacaoConverter;
import com.votacao.dto.PautaDTO;
import com.votacao.dto.PautaInclusaoDTO;
import com.votacao.dto.PautaListaDTO;
import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.entity.PautaEntity;
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
import javax.websocket.server.PathParam;
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
    public List<VotacaoInclusaoDTO> listarVotações(
            @RequestBody @Valid PautaDTO pautaDTO) {
        return votacaoService.listarVotações(pautaDTO);
    }


}
