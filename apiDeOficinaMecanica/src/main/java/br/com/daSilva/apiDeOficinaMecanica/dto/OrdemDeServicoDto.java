package br.com.daSilva.apiDeOficinaMecanica.dto;

import br.com.daSilva.apiDeOficinaMecanica.databse.model.ClienteEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.model.ServicoEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.model.VeiculoEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemDeServicoDto {

    @Column(nullable = false)
    private String descricao;
   @NotNull
    private UUID clienteId;
    @NotNull
    private UUID veiculoId;
    @NotEmpty
    private Set<UUID> servicoIds;

}
