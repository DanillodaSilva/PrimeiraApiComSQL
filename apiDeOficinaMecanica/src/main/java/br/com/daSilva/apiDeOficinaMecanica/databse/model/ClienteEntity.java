package br.com.daSilva.apiDeOficinaMecanica.databse.model;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToMany(mappedBy = "cliente")
    private Set<VeiculoEntity> veiculos;
    @OneToMany(mappedBy = "cliente")
    private Set<OrdemDeServicoEntity> ordemDeServico;

}

