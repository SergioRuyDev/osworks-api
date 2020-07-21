package com.sergioruy.osworksapi.controller;

import com.sergioruy.osworksapi.domain.service.GestaoOrdemServicoService;
import com.sergioruy.osworksapi.model.Comentario;
import com.sergioruy.osworksapi.model.ComentarioInput;
import com.sergioruy.osworksapi.model.ComentarioModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServico;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioModel adicionar(@PathVariable Long ordemServicoId,
                                     @Valid @RequestBody ComentarioInput comentarioInput) {
        Comentario comentario = gestaoOrdemServico.adicionarComantario(ordemServicoId, comentarioInput.getDescricao());

        return toModel(comentario);
    }

    private ComentarioModel toModel(Comentario comentario) {
        return modelMapper.map(comentario, ComentarioModel.class);
    }
}
