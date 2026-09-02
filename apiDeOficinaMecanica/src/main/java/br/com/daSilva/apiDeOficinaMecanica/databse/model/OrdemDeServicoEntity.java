package br.com.daSilva.apiDeOficinaMecanica.databse.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ordem_servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemDeServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private VeiculoEntity veiculo;

    @ManyToMany
    @JoinTable(
            name = "ordem_servico_servico",
            joinColumns = @JoinColumn(name = "ordem_servico_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private Set<ServicoEntity> servico = new HashSet<>();

}
