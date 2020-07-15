package com.votacao.service;

import com.votacao.converter.AssociadoConverter;
import com.votacao.dto.AssociadoInclusaoDTO;
import com.votacao.dto.AssociadoListaDTO;
import com.votacao.entity.AssociadoEntity;
import com.votacao.exception.AssociadoNotFoundException;
import com.votacao.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssociadoService {

    private AssociadoRepository associadoRepository;

    private AssociadoConverter associadoConverter;

    @Autowired
    public AssociadoService(AssociadoRepository associadoRepository, AssociadoConverter associadoConverter) {
        this.associadoRepository = associadoRepository;
        this.associadoConverter = associadoConverter;
    }

    public AssociadoEntity cadastrar(AssociadoInclusaoDTO associadoInclusaoDTO) {
        return associadoRepository.save(associadoConverter.convertToEntity(associadoInclusaoDTO));
    }

    public List<AssociadoListaDTO> listarAssociados() {
        var associados = associadoRepository.findAll();
        if (associados.isEmpty()) {
            throw new AssociadoNotFoundException("Nenhum associado foi encontrado.");
        }
        return associados.stream()
                .map(associadoConverter::convertToListaDTO)
                .collect(Collectors.toList());
    }

    public Optional<AssociadoEntity> retornaAssociadoPeloId(Long id){
        return associadoRepository.findById(id);
    }
}
