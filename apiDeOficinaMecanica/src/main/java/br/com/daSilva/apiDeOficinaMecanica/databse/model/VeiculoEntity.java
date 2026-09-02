package br.com.daSilva.apiDeOficinaMecanica.databse.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Carros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(nullable = false)
    private String modelo;
    @Column(nullable = false)
    private String placa;

    @ManyToOne
    @JoinColumn(name = "Cliente_id")
    private  ClienteEntity cliente;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private Set<OrdemDeServicoEntity> ordemDeServico = new HashSet<>();
}
