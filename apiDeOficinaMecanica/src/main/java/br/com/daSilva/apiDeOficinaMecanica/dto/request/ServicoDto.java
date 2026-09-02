package br.com.daSilva.apiDeOficinaMecanica.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;


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
