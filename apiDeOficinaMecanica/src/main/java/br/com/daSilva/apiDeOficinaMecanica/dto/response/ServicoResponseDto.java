package br.com.daSilva.apiDeOficinaMecanica.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ServicoResponseDto(UUID id,
                                 String nome,
                                 BigDecimal preco) {
}
