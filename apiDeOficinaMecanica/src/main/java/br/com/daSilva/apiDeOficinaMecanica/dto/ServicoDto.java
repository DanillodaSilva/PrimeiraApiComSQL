package br.com.daSilva.apiDeOficinaMecanica.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicoDto {
    @NotBlank
    private String nome;
    @NotBlank
    private BigDecimal preco;

}
