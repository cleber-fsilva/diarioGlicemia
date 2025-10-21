package com.cleber.diarioGlicemia.mapper;

import com.cleber.diarioGlicemia.controller.request.GlicemiaRequest;
import com.cleber.diarioGlicemia.controller.response.GlicemiaResponse;
import com.cleber.diarioGlicemia.entity.GlicemiaDiaria;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GlicemiaMapper {

    public GlicemiaDiaria toGlicemia(GlicemiaRequest request) {
        return GlicemiaDiaria.builder()
                .id(request.usuarioId())
                .jejum(request.valor())
                .posCafe(request.valor())
                .antesAlmoco(request.valor())
                .posAlmoco(request.valor())
                .antesJantar(request.valor())
                .posJantar(request.valor())
                .build();
    }

    public GlicemiaResponse toGlicemiaResponse(GlicemiaDiaria glicemiaDiaria) {
        return GlicemiaResponse.builder()
                .id(glicemiaDiaria.getId())
                .jejum(glicemiaDiaria.getJejum())
                .posCafe(glicemiaDiaria.getPosCafe())
                .antesAlmoco(glicemiaDiaria.getAntesAlmoco())
                .posAlmoco(glicemiaDiaria.getPosAlmoco())
                .antesJantar(glicemiaDiaria.getAntesJantar())
                .posJantar(glicemiaDiaria.getPosJantar())
                .build();
    }
}
