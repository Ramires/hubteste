package com.hubteste.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hubteste.contas.repository.ContasRepository;
import com.hubteste.enums.TipoSimNao;
import com.hubteste.models.Contas;

@Service
public class ContasService implements Serializable {

	private static final long serialVersionUID = -7476033233742032973L;

	@Autowired
	private ContasRepository repository;

	public boolean fazerEstorno(Long id, BigDecimal valor, String transacao) throws Exception {

		if(transacao == null || transacao.isEmpty() ) {
			throw new Exception("valor Transacao Origatorio");
		}
		if(BigDecimal.ZERO.compareTo(valor) >= 0) {
			throw new Exception("valor estorno deve ser maior que zero");
		}

		Optional<Contas> contas = repository.findById(id);
		if(TipoSimNao.NAO.getCodigo().equals(contas.get().getMatriz())
				&& contas.get().getSaldo().compareTo(valor) >= 0) {
			contas.get().getSaldo().subtract(valor);
			contas.get().setTransacao(transacao);
			repository.save(contas.get());
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	public boolean fazerTransferencia(Long idOrigem, Long idDestino, BigDecimal valor) throws Exception {

		Optional<Contas> contas = repository.findById(idOrigem);
		Optional<Contas> contasDestino = repository.findById(idDestino);
		if(contas == null || !contas.isPresent() || contasDestino == null || !contasDestino.isPresent()) {
			throw new Exception("Conta não Encontrada");
		}
		if(TipoSimNao.NAO.getCodigo().equals(contas.get().getMatriz())) {
			while(true) {
				if(contas.get().getContas() != null) {
					if(contas.get().getContas().getId().equals(idDestino)
							&& TipoSimNao.NAO.getCodigo().equals(contas.get().getContas().getMatriz())) {

						contasDestino.get().getSaldo().add(valor);
						repository.save(contasDestino.get());
						return Boolean.TRUE;
					}
				}else {
					break;
				}
			}
		}
		return Boolean.FALSE;
	}

}
