package br.com.daSilva.apiDeOficinaMecanica.databse.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemDeServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(nullable = false)
    private Optional<String> descricao;

    @ManyToOne
    @JoinColumn(name = "Cliente_id")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "Veiculo_id")
    private VeiculoEntity veiculo;

    @ManyToMany
    @JoinTable(
            name = "ordem_servico_servico",
            joinColumns = @JoinColumn(name = "ordem_servico_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private Set<ServicoEntity> servico = new HashSet<>();

}
