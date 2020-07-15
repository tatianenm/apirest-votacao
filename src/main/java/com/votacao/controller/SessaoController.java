package com.votacao.controller;

import com.votacao.converter.SessaoConverter;
import com.votacao.dto.PautaListaDTO;
import com.votacao.dto.SessaoInclusaoDTO;
import com.votacao.dto.SessaoListaDTO;
import com.votacao.entity.SessaoEntity;
import com.votacao.service.SessaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "api-rest/v1/sessao")
@RestController
@RequestMapping("api-rest/v1/sessao")
public class SessaoController {

    private SessaoService sessaoService;

    private SessaoConverter sessaoConverter;

    @Autowired
    public SessaoController(SessaoService sessaoService, SessaoConverter sessaoConverter) {
        this.sessaoService = sessaoService;
        this.sessaoConverter = sessaoConverter;
    }

    @ApiOperation(value = "Cadastro de sessão")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SessaoInclusaoDTO> iniciarSessao(@RequestParam(value = "idPauta", required = true) Long idPauta,
                                                           @RequestParam(value = "validadeMinutos", required = true) Integer validadeMinutos) {
        SessaoEntity sessaoEntity = sessaoService.iniciarSessao(idPauta, validadeMinutos);
        return ResponseEntity.ok(sessaoConverter.convertToDTO(sessaoEntity));
    }

    @ApiOperation(value = "Sessões")
    @GetMapping
    public List<SessaoListaDTO> sessoes() {
        return sessaoService.sessoes();
    }

}
