package com.sergioruy.osworksapi.domain.service;

import com.sergioruy.osworksapi.domain.exception.NegocioException;
import com.sergioruy.osworksapi.domain.model.Cliente;
import com.sergioruy.osworksapi.domain.repository.ClienteRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class CadastroClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private CadastroClienteService underTest;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        Cliente cliente = new Cliente();
        cliente.setNome("Sergio");
        cliente.setEmail("sergio@gmail.com");
        cliente.setTelefone("99999999");
    }


    @DisplayName("Teste do metodo salvar em cenario positivo")
    @Test
    public void givenClienteObject_whenCallMethodSalvar_thenReturnClienteObject() {

        //given
        Cliente cliente = new Cliente();
        cliente.setNome("Sergio");
        cliente.setEmail("sergio@gmail.com");
        cliente.setTelefone("99999999");
        //when
        underTest.salvar(cliente);

        //then
        ArgumentCaptor<Cliente> clienteArgumentCaptor = ArgumentCaptor.forClass(Cliente.class);
        verify(clienteRepository).save(clienteArgumentCaptor.capture());

        Cliente capturedCliente = clienteArgumentCaptor.getValue();

        assertThat(capturedCliente).isEqualTo(cliente);

    }

    @DisplayName("Teste do metodo salvar e estoura a exception")
    @Test
    public void givenClienteObject_whenCallMethodSalvar_thenReturnNegocioException() {

        //given
        Cliente cliente = new Cliente();
        cliente.setNome("Sergio");
        cliente.setEmail("sergio@gmail.com");
        cliente.setTelefone("99999999");

        given(clienteRepository.findByEmail(cliente.getEmail())).willThrow(NegocioException.class);

        System.out.println(clienteRepository); //make sure is mocked
        System.out.println(underTest); // make sure is mocked

        //when
        Assertions.assertThrows(NegocioException.class, () -> {
            underTest.salvar(cliente);
        });

        //then
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @DisplayName("Teste do metodo excluir em cenário positivo")
    @Test
    public void givenClienteObject_whenCallMethodExcluir_thenStatusOk() {

        //given
        long clienteId = 1L;

        willDoNothing().given(clienteRepository).deleteById(1L);

        //when
        underTest.excluir(1L);

        //then
        verify(clienteRepository, times(1)).deleteById(clienteId);

    }

}