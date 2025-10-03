package com.cleber.diarioGlicemia.controller;

import com.cleber.diarioGlicemia.controller.request.GlicemiaRequest;
import com.cleber.diarioGlicemia.entity.GlicemiaDiaria;
import com.cleber.diarioGlicemia.service.GlicemiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diarioGlicemia/medicao")
public class GlicemiaController {
//TODO colocar as ResponseEntity
    private final GlicemiaService service;

    @PostMapping()
    public GlicemiaDiaria atualizar(@RequestBody GlicemiaRequest request) {
        return service.createOrSave(request.usuarioId(), request.tipo(), request.valor());
    }

    @GetMapping
    public List<GlicemiaDiaria> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
