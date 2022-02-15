package com.sergioruy.osworksapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergioruy.osworksapi.domain.model.Cliente;
import com.sergioruy.osworksapi.domain.service.CadastroClienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CadastroClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;


    @DisplayName("JUnit teste do metodo Listar")
    @Test
    public void givenListDeClientes_whenChamaMetodoListar_thenReturnListaDeClientes() {

        //given
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Sergio");
        cliente1.setEmail("sergio@gmail.com");
        cliente1.setTelefone("99999999");

        Cliente cliente2 = new Cliente();
        cliente1.setNome("Tony");
        cliente1.setEmail("tony@gmail.com");
        cliente1.setTelefone("88888888");


        List<Cliente> clienteList = new ArrayList<>();
        clienteList.add(cliente1);
        clienteList.add(cliente2);
//        BDDMockito.given()

        //when

        //then

    }
}
