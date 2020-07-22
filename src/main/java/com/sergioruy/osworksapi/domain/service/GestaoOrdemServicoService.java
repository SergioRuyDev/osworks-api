package com.sergioruy.osworksapi.domain.service;

import com.sergioruy.osworksapi.domain.exception.EntidadeNaoEncontradaException;
import com.sergioruy.osworksapi.domain.exception.NegocioException;
import com.sergioruy.osworksapi.domain.model.Cliente;
import com.sergioruy.osworksapi.domain.model.OrdemServico;
import com.sergioruy.osworksapi.domain.model.StatusOrdemServico;
import com.sergioruy.osworksapi.domain.repository.ClienteRepository;
import com.sergioruy.osworksapi.domain.repository.ComentarioRepository;
import com.sergioruy.osworksapi.domain.repository.OrdemServicoRepository;
import com.sergioruy.osworksapi.model.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class GestaoOrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public OrdemServico criar(OrdemServico ordemServico) {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }

    public void finalizar(Long ordemServicoId) {
        OrdemServico ordemServico = buscar(ordemServicoId);

        ordemServico.finalizar();

        ordemServicoRepository.save(ordemServico);
    }

    public Comentario adicionarComantario(Long ordemServicoId, String descricao) {
        OrdemServico ordemServico = buscar(ordemServicoId);

        Comentario comentario = new Comentario();
        comentario.setDataEnvio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);

        return comentarioRepository.save(comentario);
    }

    private OrdemServico buscar(Long ordemServicoId) {
        return ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
    }
}
