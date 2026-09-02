package br.com.daSilva.apiDeOficinaMecanica.dto.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemDeServicoDto {

    @NotBlank
    private String descricao;
    @NotNull
    private UUID clienteId;
    @NotNull
    private UUID veiculoId;
    @NotEmpty
    private Set<UUID> servicoIds;

}
