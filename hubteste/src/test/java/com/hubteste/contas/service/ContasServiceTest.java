package com.hubteste.contas.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hubteste.contas.repository.ContasRepository;
import com.hubteste.enums.TipoSimNao;
import com.hubteste.models.Contas;
import com.hubteste.service.ContasService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContasServiceTest {

	@InjectMocks
	private ContasService contasService;

	@Mock
	private ContasRepository repository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testEstornoContaSemValor() throws Exception {
		Long id = 1L;
		BigDecimal valor = BigDecimal.TEN;
		String transacao = new String("A123");

		Contas conta = new Contas();
		conta.setId(id);
		conta.setMatriz(TipoSimNao.NAO.getCodigo());
		conta.setSaldo(BigDecimal.ZERO);

		Optional<Contas> contas = Optional.of(conta);

		when(repository.findById(anyLong())).thenReturn(contas);

		boolean estornoFeito = contasService.fazerEstorno(id, valor, transacao);

		assertFalse(estornoFeito);
	}

	@Test
	public void testEstornoContaMatriz() throws Exception {
		Long id = 1L;
		BigDecimal valor = BigDecimal.TEN;
		String transacao = new String("A123");

		Contas conta = new Contas();
		conta.setId(id);
		conta.setMatriz(TipoSimNao.SIM.getCodigo());
		conta.setSaldo(BigDecimal.TEN);

		Optional<Contas> contas = Optional.of(conta);

		when(repository.findById(anyLong())).thenReturn(contas);

		boolean estornoFeito = contasService.fazerEstorno(id, valor, transacao);

		assertFalse(estornoFeito);
	}

	@Test(expected = Exception.class)
	public void testEstornoTransacaoObrigatoria() throws Exception {
		Long id = 1L;
		BigDecimal valor = BigDecimal.TEN;
		String transacao = new String("");

		boolean estornoFeito = contasService.fazerEstorno(id, valor, transacao);

		assertFalse(estornoFeito);
	}

	@Test
	public void testEstornoOK() throws Exception {
		Long id = 3L;
		BigDecimal valor = BigDecimal.TEN;
		String transacao = new String("A123");

		Contas conta = new Contas();
		conta.setId(id);
		conta.setMatriz(TipoSimNao.NAO.getCodigo());
		conta.setSaldo(BigDecimal.TEN);

		Optional<Contas> contas = Optional.of(conta);

		when(repository.findById(anyLong())).thenReturn(contas);
		boolean estornoFeito = contasService.fazerEstorno(id, valor, transacao);

		assertTrue(estornoFeito);
	}

	/**
	 * TESTES Fazer Transferencia
	 */
	@Test(expected = Exception.class)
	public void testFazerTransferenciaOrigemEDestinoNaoExistente() throws Exception {

		Long idOrigem =2L;
		Long idDestino = 3L;
		BigDecimal valor = BigDecimal.TEN;

		when(repository.findById(idOrigem)).thenReturn(Optional.empty());
		when(repository.findById(idDestino)).thenReturn(Optional.empty());
		boolean tranferido = contasService.fazerTransferencia(idOrigem, idDestino, valor);

		assertTrue(tranferido);
	}

	@Test(expected = Exception.class)
	public void testFazerTransferenciaOrigemNaoExistente() throws Exception {

		Long idOrigem =2L;
		Long idDestino = 3L;
		BigDecimal valor = BigDecimal.TEN;

		Contas contaDestino = new Contas();
		contaDestino.setId(idDestino);
		contaDestino.setMatriz(TipoSimNao.NAO.getCodigo());
		contaDestino.setSaldo(BigDecimal.TEN);

		Optional<Contas> contasDestino = Optional.of(contaDestino);

		when(repository.findById(idOrigem)).thenReturn(Optional.empty());
		when(repository.findById(idDestino)).thenReturn(contasDestino);
		boolean tranferido = contasService.fazerTransferencia(idOrigem, idDestino, valor);

		assertTrue(tranferido);
	}

	@Test(expected = Exception.class)
	public void testFazerTransferenciaDestinoNaoExistente() throws Exception {

		Long idOrigem =2L;
		Long idDestino = 3L;
		BigDecimal valor = BigDecimal.TEN;

		Contas contaOrigem = new Contas();
		contaOrigem.setId(idDestino);
		contaOrigem.setMatriz(TipoSimNao.NAO.getCodigo());
		contaOrigem.setSaldo(BigDecimal.TEN);

		Optional<Contas> contasOrigem = Optional.of(contaOrigem);

		when(repository.findById(idOrigem)).thenReturn(contasOrigem);
		when(repository.findById(idDestino)).thenReturn(Optional.empty());
		boolean tranferido = contasService.fazerTransferencia(idOrigem, idDestino, valor);

		assertTrue(tranferido);
	}

	@Test
	public void testFazerTransferenciaOK() throws Exception {

		Long idOrigem =2L;
		Long idDestino = 3L;
		BigDecimal valor = BigDecimal.TEN;

		Contas contaDestino = new Contas();
		contaDestino.setId(idDestino);
		contaDestino.setMatriz(TipoSimNao.NAO.getCodigo());
		contaDestino.setSaldo(BigDecimal.TEN);

		Contas conta = new Contas();
		conta.setId(idOrigem);
		conta.setMatriz(TipoSimNao.NAO.getCodigo());
		conta.setSaldo(BigDecimal.TEN);
		conta.setContas(contaDestino);

		Optional<Contas> contasOrigem = Optional.of(conta);
		Optional<Contas> contasDestino = Optional.of(contaDestino);

		when(repository.findById(idOrigem)).thenReturn(contasOrigem);
		when(repository.findById(idDestino)).thenReturn(contasDestino);
		boolean tranferido = contasService.fazerTransferencia(idOrigem, idDestino, valor);

		assertTrue(tranferido);
	}
}
