package com.votacao.controller;

import com.votacao.converter.SessaoConverter;
import com.votacao.dto.SessaoDTO;
import com.votacao.entity.SessaoEntity;
import com.votacao.service.SessaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "api-rest/v1/sessao")
@RestController
@RequestMapping("api-rest/v1/sessao")
public class SessaoController {

    private SessaoService sessaoService;

    private SessaoConverter sessaoConverter;

    public SessaoController(SessaoService sessaoService, SessaoConverter sessaoConverter) {
        this.sessaoService = sessaoService;
        this.sessaoConverter = sessaoConverter;
    }

    @ApiOperation(value = "Cadastro de sessão")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SessaoDTO> iniciarSessao(Long idPauta, Integer validade) {
        SessaoEntity sessaoEntity = sessaoService.iniciarSessao(idPauta, validade);
        return ResponseEntity.ok(sessaoConverter.convertToDTO(sessaoEntity));
    }

}
