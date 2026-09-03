package br.com.daSilva.apiDeOficinaMecanica.service;

import br.com.daSilva.apiDeOficinaMecanica.databse.model.ClienteEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.model.VeiculoEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IClienteRepository;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IOrdemDeServicoRepository;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IVeiculoRepository;
import br.com.daSilva.apiDeOficinaMecanica.dto.request.VeiculoRequestDto;
import br.com.daSilva.apiDeOficinaMecanica.dto.response.VeiculoResponseDto;
import br.com.daSilva.apiDeOficinaMecanica.exception.BadRequestExceptionn;
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
    public void criarVeiculo(VeiculoRequestDto dto) {
        ClienteEntity clientes = cliente.findById(dto.getClienteId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
        VeiculoEntity carro = veiculo.findByPlaca(dto.getPlaca()).orElse(null);
        if (carro != null) {
            throw new BadRequestExceptionn("Placa de veiculo já cadastrada");
        }
        carro = VeiculoEntity.builder()
                .modelo(dto.getModelo())
                .placa(dto.getPlaca())
                .cliente(clientes)
                .build();
        veiculo.save(carro);
    }

    //    FIND VEICULO BY ID
    public VeiculoResponseDto findVeiculoById(UUID id) {

        VeiculoEntity veiculoEntity = veiculo
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Veiculo não encontrado"));

        return new VeiculoResponseDto(
                veiculoEntity.getId(),
                veiculoEntity.getModelo(),
                veiculoEntity.getPlaca(),
                veiculoEntity.getCliente().getId()
        );
    }

    //    FIND VEICULO
    public List<VeiculoResponseDto> findVeiculo() {
      List<VeiculoEntity> veiculoEntity = veiculo.findAll();

        return veiculoEntity
                .stream()
                .map(carro -> new VeiculoResponseDto(
                        carro.getId(),
                        carro.getModelo(),
                        carro.getPlaca(),
                        carro.getCliente().getId()
                ))
                .toList();

    }

    //    DELETE VEICULO BY ID
    public void deleteVeiculo(UUID id) {
        VeiculoEntity veiculoEntity = veiculo.findById(id).orElse(null);
        if (veiculoEntity == null) {
            throw new NotFoundException("Veiculo não encontrado");
        }
        UUID ordem = veiculoEntity
                .getOrdemDeServico()
                .getId();
        ordemDeServico.deleteById(ordem);
        veiculo.deleteById(id);
    }
}
