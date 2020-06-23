package com.sergioruy.osworksapi.controller;

import com.sergioruy.osworksapi.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        var cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("João");
        cliente1.setTelefone("34 99999-1111");
        cliente1.setEmail("joaodascoves@gmail.com");

        var cliente2 = new Cliente();
        cliente2.setId(1L);
        cliente2.setNome("Maria");
        cliente2.setTelefone("34 88888-2222");
        cliente2.setEmail("mariaclara@gmail.com");

        return Arrays.asList(cliente1, cliente2);
    }
}
