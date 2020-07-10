package com.votacao.service;

import com.votacao.converter.PautaConverter;
import com.votacao.dto.PautaInclusaoDTO;
import com.votacao.dto.PautaListaDTO;
import com.votacao.entity.PautaEntity;
import com.votacao.exception.PautaNotFoundException;
import com.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PautaService {

    private PautaRepository pautaRepository;

    private PautaConverter pautaConverter;

    @Autowired
    public PautaService(PautaRepository pautaRepository, PautaConverter pautaConverter) {
        this.pautaRepository = pautaRepository;
        this.pautaConverter = pautaConverter;
    }

    public PautaEntity cadastrar(PautaInclusaoDTO pautaInclusaoDTO) {
        return pautaRepository.save(pautaConverter.convertToEntity(pautaInclusaoDTO));
    }

    public List<PautaListaDTO> listarPautas() {
        var pautas = pautaRepository.findAll();
        if (pautas.isEmpty()) {
            throw new PautaNotFoundException("Nenhuma pauta foi encontrada");
        }
        return pautas.stream()
                .map(pautaConverter::convertToListaDTO)
                .collect(Collectors.toList());
    }
}
