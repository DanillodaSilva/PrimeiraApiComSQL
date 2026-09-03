package br.com.daSilva.apiDeOficinaMecanica.dto.response;

import java.util.Set;
import java.util.UUID;

public record OrdemServicoResponseDto(String descricao,
                                      UUID clienteId,
                                      VeiculoResponseDto veiculoId,
                                      Set<ServicoResponseDto> servicosId,
                                      UUID id) {
}
