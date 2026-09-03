package br.com.daSilva.apiDeOficinaMecanica.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicoRequestDto {
    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal preco;
}
