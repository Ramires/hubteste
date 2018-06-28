package com.hubteste.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hubteste.contas.repository.ContasRepository;
import com.hubteste.models.Contas;
import com.hubteste.service.ContasService;

@RestController
@RequestMapping("/contas")
public class ContasController {

	@Autowired
	private ContasRepository repository;

	@Autowired
	private ContasService service;

	@GetMapping
	public List<Contas> listarTodos() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Contas> getContas(@PathVariable("id") Long id) {
		return repository.findById(id);
	}

	@PostMapping("/{id}{valor}{transacao}")
	public ResponseEntity<?> fazerEstorno(@PathVariable("id") Long id, @PathVariable("valor") BigDecimal valor, @PathVariable("transacao") String transacao) {
		try {
			service.fazerEstorno(id, valor, transacao);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/{idOrigem}{idDestino}{valor}")
	public ResponseEntity<?> fazerEstorno(@PathVariable("idOrigem") Long idOrigem, @PathVariable("idDestino") Long idDestino, @PathVariable("valor") BigDecimal valor) {
		try {
			service.fazerTransferencia(idOrigem, idDestino, valor);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody Contas contas) {
		repository.save(contas);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody Contas contas) {
		repository.save(contas);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		repository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
