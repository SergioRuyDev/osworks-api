package com.sergioruy.osworksapi.controller;

import com.sergioruy.osworksapi.domain.model.OrdemServico;
import com.sergioruy.osworksapi.domain.service.GestaoOrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServico;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return gestaoOrdemServico.criar(ordemServico);

    }
}
