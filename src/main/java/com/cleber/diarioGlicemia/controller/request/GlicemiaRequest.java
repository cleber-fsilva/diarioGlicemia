package com.cleber.diarioGlicemia.controller.request;

import java.time.LocalDate;

public record GlicemiaRequest(LocalDate data, String tipo, Double valor, Long usuarioId) {
}
