package br.com.daSilva.apiDeOficinaMecanica.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequestDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
}

