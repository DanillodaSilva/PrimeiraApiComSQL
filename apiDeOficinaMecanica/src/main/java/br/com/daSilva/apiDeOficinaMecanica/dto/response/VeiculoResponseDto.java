package br.com.daSilva.apiDeOficinaMecanica.dto.response;

import java.util.UUID;

public record VeiculoResponseDto(
        UUID id,
        String modelo,
        String placa,
        UUID clienteId) {
}
