package br.com.daSilva.apiDeOficinaMecanica.dto;


import br.com.daSilva.apiDeOficinaMecanica.databse.model.OrdemDeServicoEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.model.VeiculoEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotEmpty
    private Set<VeiculoEntity> veiculos;
    @NotEmpty
    private Set<OrdemDeServicoEntity> ordemDeServico;
}

