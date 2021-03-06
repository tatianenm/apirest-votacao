package com.votacao.controller;

import com.votacao.converter.AssociadoConverter;
import com.votacao.dto.AssociadoInclusaoDTO;
import com.votacao.dto.AssociadoListaDTO;
import com.votacao.entity.AssociadoEntity;
import com.votacao.service.AssociadoService;
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

@Api(value = "api-rest/v1/associados")
@RestController
@RequestMapping("api-rest/v1/associados")
public class AssosciadoController {

    private AssociadoService associadoService;

    private AssociadoConverter associadoConverter;

    @Autowired
    public AssosciadoController(AssociadoService associadoService, AssociadoConverter associadoConverter) {
        this.associadoService = associadoService;
        this.associadoConverter = associadoConverter;
    }

    @ApiOperation(value = "Cadastro de associado")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AssociadoInclusaoDTO> cadastrar(
            @RequestBody @Valid AssociadoInclusaoDTO associadoInclusaoDTO, UriComponentsBuilder uriBuilder) {
        AssociadoEntity associadoEntity = associadoService.cadastrar(associadoInclusaoDTO);
        URI uri = uriBuilder.path("/associados/{id}").buildAndExpand(associadoEntity.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(associadoConverter.convertToDTO(associadoEntity));
    }

    @ApiOperation(value = "Associados")
    @GetMapping
    public List<AssociadoListaDTO> associados() {
        return associadoService.associados();
    }
}
