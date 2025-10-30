package com.cleber.diarioGlicemia.service;

import com.cleber.diarioGlicemia.controller.response.GlicemiaResponse;
import com.cleber.diarioGlicemia.entity.GlicemiaDiaria;
import com.cleber.diarioGlicemia.entity.User;
import com.cleber.diarioGlicemia.repository.GlicemiaRepository;
import com.cleber.diarioGlicemia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GlicemiaService {

    private final GlicemiaRepository repository;
    private final UserRepository userRepository;

    //Cria ou atualiza a linha com no periodo selecionado
    public GlicemiaDiaria createOrSave(Long usuarioId, LocalDate dataMedicao, String tipo, Double valor) {
        User usuario = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        //LocalDate hoje = LocalDate.now();

        // verifica se ja existe uma linha para hoje
        GlicemiaDiaria registro = repository.findByDataAndUser(dataMedicao, userRepository.getReferenceById(usuarioId))
                .orElse(new GlicemiaDiaria(dataMedicao));

        registro.setUser(usuario);
        registro.setData(dataMedicao);

        switch (tipo.toLowerCase()) {
            case "jejum":
                registro.setJejum(valor);
                break;
            case "pos_cafe":
                registro.setPosCafe(valor);
                break;
            case "antes_almoco":
                registro.setAntesAlmoco(valor);
                break;
            case "pos_almoco":
                registro.setPosAlmoco(valor);
                break;
            case "antes_jantar":
                registro.setAntesJantar(valor);
                break;
            case "pos_jantar" :
                registro.setPosJantar(valor);
                break;
            default: throw new IllegalArgumentException("Tipo periodo inválido");
        }
        return repository.save(registro);
    }

    public List<GlicemiaDiaria> findAll() {
        return repository.findAll();
    }

    public List<GlicemiaResponse> findByUserId(Long usuarioId) {
        return repository.findByUser_Id(usuarioId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
