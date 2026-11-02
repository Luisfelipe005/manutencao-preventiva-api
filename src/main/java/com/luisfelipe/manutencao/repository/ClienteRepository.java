package com.luisfelipe.manutencao.repository;

import com.luisfelipe.manutencao.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
