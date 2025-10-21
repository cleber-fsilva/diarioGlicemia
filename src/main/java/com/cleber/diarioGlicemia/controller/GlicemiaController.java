package com.cleber.diarioGlicemia.controller;

import com.cleber.diarioGlicemia.controller.request.GlicemiaRequest;
import com.cleber.diarioGlicemia.controller.response.GlicemiaResponse;
import com.cleber.diarioGlicemia.entity.GlicemiaDiaria;
import com.cleber.diarioGlicemia.mapper.GlicemiaMapper;
import com.cleber.diarioGlicemia.service.GlicemiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diarioGlicemia/medicao")
public class GlicemiaController {

    private final GlicemiaService service;

    @PostMapping()
    public ResponseEntity<GlicemiaRequest> criarOuAtualizar(@RequestBody GlicemiaRequest request) {
        service.createOrSave(request.usuarioId(), request.tipo(), request.valor());
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }

    @GetMapping
    public ResponseEntity<List<GlicemiaResponse>> findAll() {
        List<GlicemiaResponse> glicemias = service.findAll()
                .stream()
                .map(glicemia -> GlicemiaMapper.toGlicemiaResponse(glicemia))
                .toList();


        return ResponseEntity.ok(glicemias);
    }

    @GetMapping("/user/{usuarioId}")
    public List<GlicemiaResponse> getMedicoesPorUsuario(@PathVariable Long usuarioId) {
        return service.findByUserId(usuarioId);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
