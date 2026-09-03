package br.com.daSilva.apiDeOficinaMecanica.service;

import br.com.daSilva.apiDeOficinaMecanica.databse.model.ClienteEntity;

import br.com.daSilva.apiDeOficinaMecanica.databse.model.OrdemDeServicoEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.model.VeiculoEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IClienteRepository;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IOrdemDeServicoRepository;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IVeiculoRepository;
import br.com.daSilva.apiDeOficinaMecanica.dto.request.ClienteDto;
import br.com.daSilva.apiDeOficinaMecanica.dto.response.ClienteResponseDto;
import br.com.daSilva.apiDeOficinaMecanica.dto.response.VeiculoResponseDto;
import br.com.daSilva.apiDeOficinaMecanica.exception.BadRequestExceptionn;
import br.com.daSilva.apiDeOficinaMecanica.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final IClienteRepository clienteRepository;
    private final IOrdemDeServicoRepository ordemDeServicoRepository;
    private final IVeiculoRepository veiculoRepository;

    //  CRIAR CLIENTE - POST
    public void criarCliente(ClienteDto clienteDto) throws BadRequestExceptionn {
        ClienteEntity cliente = clienteRepository.findByEmail(clienteDto.getEmail()).orElse(null);
        if (cliente != null) {
            throw new BadRequestExceptionn("Email ja cadastrado");
        }
        cliente = ClienteEntity.builder()
                .email(clienteDto.getEmail())
                .nome(clienteDto.getNome())
                .build();
        clienteRepository.save(cliente);
    }

    //  BUSCAR POR ID - GET
    public ClienteResponseDto findClienteById(UUID id) throws NotFoundException {
        ClienteEntity cliente = clienteRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        Set<VeiculoResponseDto> veiculos = cliente.getVeiculos()
                .stream()
                .map(veiculo -> new VeiculoResponseDto(
                        veiculo.getId(),
                        veiculo.getModelo(),
                        veiculo.getPlaca(),
                        veiculo.getCliente().getId()
                ))
                .collect(Collectors.toSet());

        return new ClienteResponseDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                veiculos
        );
    }

    //  BUSCAR POR EMAIL - GET
    public ClienteResponseDto findClienteByEmail(String email) throws NotFoundException {

        ClienteEntity cliente = clienteRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        Set<VeiculoResponseDto> veiculos = cliente.getVeiculos()
                .stream()
                .map(veiculo -> new VeiculoResponseDto(
                        veiculo.getId(),
                        veiculo.getModelo(),
                        veiculo.getPlaca(),
                        veiculo.getCliente().getId()
                ))
                .collect(Collectors.toSet());

        return new ClienteResponseDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                veiculos
        );
    }
//  DELETAR CLIENTE - DELETE
    @Transactional
    public void deletarClientePeloId(UUID id){
        ClienteEntity cliente = clienteRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        List<UUID> carro = cliente.getVeiculos().stream().map(VeiculoEntity::getId).toList();
        List<UUID> ordem = cliente.getOrdemDeServico().stream().map(OrdemDeServicoEntity::getId).toList();
        ordemDeServicoRepository.deleteAllById(ordem);
        veiculoRepository.deleteAllById(carro);
        clienteRepository.deleteById(cliente.getId());
    }

    public List<ClienteResponseDto> findAll() {

        return clienteRepository.findAll()
                .stream()
                .map(cliente -> {

                    Set<VeiculoResponseDto> veiculos = cliente.getVeiculos()
                            .stream()
                            .map(veiculo -> new VeiculoResponseDto(
                                    veiculo.getId(),
                                    veiculo.getModelo(),
                                    veiculo.getPlaca(),
                                    veiculo.getCliente().getId()
                            ))
                            .collect(Collectors.toSet());

                    return new ClienteResponseDto(
                            cliente.getId(),
                            cliente.getNome(),
                            cliente.getEmail(),
                            veiculos
                    );
                })
                .toList();
    }
}
