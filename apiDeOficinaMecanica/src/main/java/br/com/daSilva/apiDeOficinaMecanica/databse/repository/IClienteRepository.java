package br.com.daSilva.apiDeOficinaMecanica.databse.repository;

import br.com.daSilva.apiDeOficinaMecanica.databse.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IClienteRepository extends JpaRepository<ClienteEntity, UUID> {
    Optional<ClienteEntity> findByEmail(String email);
}
