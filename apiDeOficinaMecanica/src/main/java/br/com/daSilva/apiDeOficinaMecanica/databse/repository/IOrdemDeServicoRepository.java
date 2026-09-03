package br.com.daSilva.apiDeOficinaMecanica.databse.repository;

import br.com.daSilva.apiDeOficinaMecanica.databse.model.ClienteEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.model.OrdemDeServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IOrdemDeServicoRepository extends JpaRepository<OrdemDeServicoEntity, UUID> {
    Optional<OrdemDeServicoEntity> findByVeiculoId(UUID id);
}
