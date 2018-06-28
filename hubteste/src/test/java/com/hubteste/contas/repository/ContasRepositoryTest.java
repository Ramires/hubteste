package com.hubteste.contas.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.hubteste.models.Contas;

@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class ContasRepositoryTest {

	@Autowired
	private ContasRepository repository;

	@Test
	public void testBuscarPorId() throws Exception {
		Long esperado = 1L;
		Optional<Contas> contas = repository.findById(esperado);

		assertNotNull(contas);
		assertEquals(esperado, contas.get().getId());
	}

	@Test
	public void testRemover() throws Exception {

		repository.deleteById(1L);

		Long esperado = 1L;
		Optional<Contas> contas = repository.findById(esperado);

		assertFalse(contas.isPresent());
	}


}
