package com.sergioruy.osworksapi.domain.service;

import com.sergioruy.osworksapi.domain.exception.NegocioException;
import com.sergioruy.osworksapi.domain.model.Cliente;
import com.sergioruy.osworksapi.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente salvar(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

        if (clienteExistente != null && !clienteExistente.equals(cliente)) {
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
        }

        return clienteRepository.save(cliente);
    }

    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
