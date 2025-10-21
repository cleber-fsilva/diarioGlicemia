package com.cleber.diarioGlicemia.controller.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record GlicemiaResponse(Long id,
                               LocalDate data,
                               Double jejum,
                               Double posCafe,
                               Double antesAlmoco,
                               Double posAlmoco,
                               Double antesJantar,
                               Double posJantar){

}
