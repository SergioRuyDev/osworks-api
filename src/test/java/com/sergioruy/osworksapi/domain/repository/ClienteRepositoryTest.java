package com.sergioruy.osworksapi.domain.repository;

import com.sergioruy.osworksapi.domain.model.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository underTest;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        Cliente cliente = new Cliente();
        cliente.setNome("Sergio");
        cliente.setEmail("sergio@gmail.com");
        cliente.setTelefone("99999999");
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }


    @Test
    public void givenClienteEmail_whenFindByEmail_thenReturnClienteObject() {

        //given
        Cliente cliente = new Cliente();
        cliente.setNome("Sergio");
        cliente.setEmail("sergio@gmail.com");
        cliente.setTelefone("99999999");
        underTest.save(cliente);
        //when
        Cliente clientDb = underTest.findAllByEmail(cliente.getEmail());

        //then
        assertThat(clientDb).isNotNull();
        assertThat(clientDb.getEmail()).isEqualTo("sergio@gmail.com");

    }

}