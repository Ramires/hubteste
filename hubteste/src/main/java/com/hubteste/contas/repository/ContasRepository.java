package com.hubteste.contas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hubteste.models.Contas;

public interface ContasRepository extends JpaRepository<Contas, Long> {
}
