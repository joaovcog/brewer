package com.algaworks.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.brewer.model.Cliente;
import com.algaworks.brewer.repository.helper.cliente.ClienteRepositoryQueries;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryQueries {

	public Optional<Cliente> findByCpfCnpj(String cpfCnpj);

	public List<Cliente> findByNomeStartingWithIgnoreCase(String nome);
	
}
