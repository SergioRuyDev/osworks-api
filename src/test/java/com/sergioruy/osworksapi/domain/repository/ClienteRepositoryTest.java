package com.sergioruy.osworksapi.domain.repository;

import com.sergioruy.osworksapi.domain.model.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void CheckFindAllByEmail() {
        //given
        String email = "sergio@gmail.com";
        Cliente cliente = new Cliente(1L, "Sergio", email, "99999999");
        underTest.save(cliente);

        //when
        Cliente expected = underTest.findAllByEmail(email);

        //then
        assertThat(expected).asString();
    }

}