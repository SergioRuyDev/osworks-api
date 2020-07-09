package com.sergioruy.osworksapi.domain.service;

import com.sergioruy.osworksapi.domain.exception.NegocioException;
import com.sergioruy.osworksapi.domain.model.Cliente;
import com.sergioruy.osworksapi.domain.model.OrdemServico;
import com.sergioruy.osworksapi.domain.model.StatusOrdemServico;
import com.sergioruy.osworksapi.domain.repository.ClienteRepository;
import com.sergioruy.osworksapi.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GestaoOrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public OrdemServico criar(OrdemServico ordemServico) {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }
}
