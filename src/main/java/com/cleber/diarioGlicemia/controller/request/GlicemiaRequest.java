package com.cleber.diarioGlicemia.controller.request;

public record GlicemiaRequest(String tipo, Double valor, Long usuarioId) {
}
