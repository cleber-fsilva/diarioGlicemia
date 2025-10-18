package com.cleber.diarioGlicemia.controller.response;

import lombok.Builder;

@Builder
public record GlicemiaResponse(Long id,
                               Double jejum,
                               Double pos_cafe,
                               Double antesAlmoco,
                               Double posAlmoco,
                               Double antesJantar,
                               Double posJantar){

}
