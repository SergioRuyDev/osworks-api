package com.sergioruy.osworksapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergioruy.osworksapi.domain.model.Cliente;
import com.sergioruy.osworksapi.domain.service.CadastroClienteService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void givenListDeClientes_whenChamaMetodoListar_thenReturnListaDeClientes() throws Exception {

        //given
        List<Cliente> clienteList = new ArrayList<>();
        clienteList.add(Cliente.builder().nome("Sergio").email("sergio@gmail.com").telefone("99999999").build());
        clienteList.add(Cliente.builder().nome("Tony").email("tony@gmail.com").telefone("88888888").build());
        given(clienteService.getAllClientes()).willReturn(clienteList);

        //when
        ResultActions response = mockMvc.perform(get("/clientes"));

        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(clienteList.size())));

    }
}
