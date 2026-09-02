package br.com.daSilva.apiDeOficinaMecanica.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull
    @Positive
    private BigDecimal preco;
}
