package br.com.daSilva.apiDeOficinaMecanica.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeiculoDto {
    @NotBlank
    private String modelo;
    @NotBlank
    private String placa;
    @NotNull
    private UUID clienteId;
}
