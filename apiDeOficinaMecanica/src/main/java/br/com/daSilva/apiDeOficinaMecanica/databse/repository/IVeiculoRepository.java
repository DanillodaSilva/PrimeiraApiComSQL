package br.com.daSilva.apiDeOficinaMecanica.databse.repository;

import br.com.daSilva.apiDeOficinaMecanica.databse.model.ClienteEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.model.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IVeiculoRepository extends JpaRepository<VeiculoEntity, UUID> {
}
