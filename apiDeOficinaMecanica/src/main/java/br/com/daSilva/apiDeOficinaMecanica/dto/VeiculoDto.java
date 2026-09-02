package br.com.daSilva.apiDeOficinaMecanica.dto;

import br.com.daSilva.apiDeOficinaMecanica.databse.model.ClienteEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.model.OrdemDeServicoEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
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
