package br.com.daSilva.apiDeOficinaMecanica.databse.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private BigDecimal preco;

}
