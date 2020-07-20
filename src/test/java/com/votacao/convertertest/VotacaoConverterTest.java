package com.votacao.convertertest;

import com.votacao.converter.AssociadoConverter;
import com.votacao.converter.SessaoConverter;
import com.votacao.converter.VotacaoConverter;
import com.votacao.domain.VotoEnum;
import com.votacao.dto.AssociadoDTO;
import com.votacao.dto.SessaoDTO;
import com.votacao.dto.VotacaoInclusaoDTO;
import com.votacao.entity.AssociadoEntity;
import com.votacao.entity.SessaoEntity;
import com.votacao.entity.VotacaoEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
public class VotacaoConverterTest {

    private static final Long ID = 2L;

    private static final Long ID_SESSAO = 3L;

    private static final Long ID_ASSOCIADO = 6L;

    private static final LocalDate DATA_SISTEMA = LocalDate.now();

    private static final VotoEnum VOTO_SIM = VotoEnum.SIM;

    @InjectMocks
    private VotacaoConverter votacaoConverter;
    @Mock
    private SessaoConverter sessaoConverter;
    @Mock
    private AssociadoConverter associadoConverter;

    @Test
    public void deveConverterToEntity() {
        var inclusaoDTO = mockVotacaoInclusaoDTO();
        Mockito.doCallRealMethod().when(sessaoConverter).convertToEntity(Mockito.any(SessaoDTO.class));

        var votacao = votacaoConverter.convertToEntity(inclusaoDTO);

        Assert.assertNotNull(votacao);
        Assert.assertEquals(votacao.getSessao().getId(), inclusaoDTO.getSessao().getId());
        Assert.assertEquals(votacao.getDataSistema(), inclusaoDTO.getDataSistema());
        Assert.assertEquals(votacao.getVoto(), inclusaoDTO.getVoto());
        Mockito.verify(sessaoConverter, Mockito.times(1))
                .convertToEntity(Mockito.any(SessaoDTO.class));

    }

    private VotacaoInclusaoDTO mockVotacaoInclusaoDTO() {
        return VotacaoInclusaoDTO.builder()
                .id(ID)
                .sessao(mockSessaoDTO())
                .associado(mockAssociadoDTO())
                .dataSistema(DATA_SISTEMA)
                .voto(VotoEnum.SIM)
                .build();
    }

    private AssociadoDTO mockAssociadoDTO() {
        return AssociadoDTO.builder().id(ID_ASSOCIADO).build();
    }

    private SessaoDTO mockSessaoDTO() {
        return SessaoDTO.builder().id(ID_SESSAO).build();
    }

    @Test
    public void deveConverterToDTO() {
        var votacao = mockVotacaoEntity();
        Mockito.doCallRealMethod().when(sessaoConverter).convertToEntity(Mockito.any(SessaoDTO.class));

        var votacaoInclusao = votacaoConverter.convertToDTO(votacao);

        Assert.assertNotNull(votacaoInclusao);
        Assert.assertEquals(votacaoInclusao.getDataSistema(), votacao.getDataSistema());
        Assert.assertEquals(votacaoInclusao.getVoto(), votacao.getVoto());

    }

    private VotacaoEntity mockVotacaoEntity() {
        return VotacaoEntity.builder()
                .associado(mockAssociado())
                .dataSistema(DATA_SISTEMA)
                .id(ID)
                .voto(VOTO_SIM)
                .sessao(mockSessao())
                .build();
    }

    private AssociadoEntity mockAssociado() {
        return AssociadoEntity.builder().id(ID_ASSOCIADO).build();
    }

    private SessaoEntity mockSessao() {
        return SessaoEntity.builder().id(ID_SESSAO).build();
    }

}
