package br.com.daSilva.apiDeOficinaMecanica.service;

import br.com.daSilva.apiDeOficinaMecanica.databse.model.ClienteEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.model.OrdemDeServicoEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.model.VeiculoEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IClienteRepository;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IOrdemDeServicoRepository;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IVeiculoRepository;
import br.com.daSilva.apiDeOficinaMecanica.dto.VeiculoDto;
import br.com.daSilva.apiDeOficinaMecanica.exception.BadRequestException;
import br.com.daSilva.apiDeOficinaMecanica.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VeiculoService {
    private final IVeiculoRepository veiculo;
    private final IClienteRepository cliente;
    private final IOrdemDeServicoRepository ordemDeServico;

    //  CRIAR CARRO
    public void criarVeiculo(VeiculoDto dto) {
        ClienteEntity clienteEntity = cliente
                .findById(dto.getClienteId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
        VeiculoEntity carro = veiculo.findByPlaca(dto.getPlaca()).orElse(null);
        if (carro != null) {
            throw new BadRequestException("Placa de veiculo já cadastrada");
        }
        carro = VeiculoEntity.builder()
                .modelo(dto.getModelo())
                .placa(dto.getPlaca())
                .cliente(clienteEntity)
                .build();
        veiculo.save(carro);
    }

    //    FIND VEICULO BY ID
    public VeiculoEntity findVeiculoById(UUID id) {
        return veiculo
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Veiculo não encontrado"));
    }

    //    FIND VEICULO
    public List<VeiculoEntity> findVeiculo() {
        return veiculo.findAll();
    }

    //    DELETE VEICULO BY ID
    public void deleteVeiculo(UUID id) {
        VeiculoEntity veiculoEntity = veiculo.findById(id).orElse(null);
        if (veiculoEntity == null) {
            throw new NotFoundException("Veiculo não encontrado");
        }
        List<UUID> ordem = veiculoEntity
                .getOrdemDeServico()
                .stream()
                .map(OrdemDeServicoEntity::getId)
                .toList();
        ordemDeServico.deleteAllById(ordem);
        veiculo.deleteById(id);
    }
}
