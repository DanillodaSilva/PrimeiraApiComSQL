package br.com.daSilva.apiDeOficinaMecanica.dto.response;

import java.util.Set;
import java.util.UUID;

public record ClienteResponseDto(UUID id,
                                 String nome,
                                 String email,
                                 Set<VeiculoResponseDto> veiculos) {

}
