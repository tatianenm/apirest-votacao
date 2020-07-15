package com.votacao.servicetest;

import com.votacao.converter.VotacaoConverter;
import com.votacao.dto.SessaoDTO;
import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.exception.VotoException;
import com.votacao.service.SessaoService;
import com.votacao.service.VotacaoService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class VotacaoServiceTest {

    @InjectMocks
    private VotacaoService votacaoService;
    @Mock
    private VotacaoConverter votacaoConverter;
    @Mock
    private SessaoService sessaoService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void salvarVotacaoDeveLancarExcecaoSeExcecaoExpirada() {
        thrown.expect(VotoException.class);
        thrown.expectMessage("Não é possível cadastrar voto, sessão expirada.");

        Mockito.doReturn(true).when(sessaoService).verificaSessaoExpirada(Mockito.any());

        votacaoService.salvarVotacao(VotacaoInclusaoDTO.builder()
                .sessao(SessaoDTO.builder().id(5L).build()).build());
        Mockito.verify(sessaoService, Mockito.times(1)).verificaSessaoExpirada(Mockito.any());

    }

}

