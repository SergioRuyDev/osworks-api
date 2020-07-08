package com.sergioruy.osworksapi.domain.repository;


import com.sergioruy.osworksapi.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findAllByNome(String nome);
    List<Cliente> findAllByNomeContaining(String nome);
    Cliente findAllByEmail(String email);
}
