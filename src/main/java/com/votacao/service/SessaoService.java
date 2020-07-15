package com.votacao.service;

import com.votacao.converter.SessaoConverter;
import com.votacao.domain.StatusSessaoEnum;
import com.votacao.dto.SessaoListaDTO;
import com.votacao.entity.PautaEntity;
import com.votacao.entity.SessaoEntity;
import com.votacao.exception.SessaoNotFoundException;
import com.votacao.repository.SessaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessaoService {

    private SessaoRepository sessaoRepository;

    private SessaoConverter sessaoConverter;

    public SessaoService(SessaoRepository sessaoRepository, SessaoConverter sessaoConverter) {
        this.sessaoRepository = sessaoRepository;
        this.sessaoConverter = sessaoConverter;
    }

    public SessaoEntity iniciarSessao(Long idPauta, Integer validade) {
        if (validade == null || validade < 1) {
            validade = 1;
        }

        return sessaoRepository.save(criarSessaoEntity(idPauta, validade));
    }

    private SessaoEntity criarSessaoEntity(Long idPauta, Integer validade) {
        return SessaoEntity.builder()
                    .dataHoraInicio(LocalDateTime.now())
                    .statusSessao(StatusSessaoEnum.INICIALIZADA)
                    .pauta(PautaEntity.builder().id(idPauta).build())
                    .validade(validade)
                    .build();
    }

    private SessaoEntity finalizarSessao(Long idSessao) {
        var sessao = findById(idSessao);
        sessao.setStatusSessao(StatusSessaoEnum.FINALIZADA);
        return sessaoRepository.save(sessao);
    }

    public Boolean verificaSessaoExpirada(Long idSessao) {
        var sessao = findById(idSessao);
        var minutos = ChronoUnit.MINUTES.between(sessao.getDataHoraInicio(), LocalDateTime.now());
        var expirado = minutos > sessao.getValidade();
        if (expirado) {
            finalizarSessao(idSessao);
        }
        return expirado;
    }

    private SessaoEntity findById(Long idSessao) {
        return sessaoRepository.findById(idSessao)
                 .orElseThrow(() -> new SessaoNotFoundException("Sessão não Encontrada."));

    }

    public List<SessaoListaDTO> sessoes() {
       var sessoes = sessaoRepository.findAll();
       if(sessoes.isEmpty()){
           throw new SessaoNotFoundException("Nenhuma sessão foi encontrada.");
       }
       return sessoes.stream()
               .map(sessaoConverter::convertToListaDTO)
               .collect(Collectors.toList());
    }
}
