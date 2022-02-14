package com.sergioruy.osworksapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergioruy.osworksapi.domain.service.CadastroClienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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

        //when

        //then

    }
}
