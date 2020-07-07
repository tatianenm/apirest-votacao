package com.votacao.controller;

import com.votacao.converter.PautaConverter;
import com.votacao.dto.PautaInclusaoDTO;
import com.votacao.entity.PautaEntity;
import com.votacao.service.PautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Api(value = "api-rest/v1/pautas")
@RestController
@RequestMapping("api-rest/v1/pautas")
public class PautaController {

    private PautaService pautaService;

    private PautaConverter pautaConverter;

    @Autowired
    public PautaController(PautaService pautaService, PautaConverter pautaConverter) {
        this.pautaService = pautaService;
        this.pautaConverter = pautaConverter;
    }

    @ApiOperation(value = "Cadastro de pauta")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PautaInclusaoDTO> cadastrar(
            @RequestBody @Valid PautaInclusaoDTO pautaInclusaoDTO, UriComponentsBuilder uriBuilder) {
        PautaEntity pautaEntity = pautaService.cadastrar(pautaInclusaoDTO);
        URI uri = uriBuilder.path("/pautas/{id}").buildAndExpand(pautaEntity.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(pautaConverter.convertToDTO(pautaEntity));
    }


}
