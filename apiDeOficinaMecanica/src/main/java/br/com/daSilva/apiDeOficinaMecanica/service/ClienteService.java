package br.com.daSilva.apiDeOficinaMecanica.service;

import br.com.daSilva.apiDeOficinaMecanica.databse.model.ClienteEntity;

import br.com.daSilva.apiDeOficinaMecanica.databse.model.OrdemDeServicoEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.model.VeiculoEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IClienteRepository;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IOrdemDeServicoRepository;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IVeiculoRepository;
import br.com.daSilva.apiDeOficinaMecanica.dto.ClienteDto;
import br.com.daSilva.apiDeOficinaMecanica.exception.BadRequestException;
import br.com.daSilva.apiDeOficinaMecanica.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final IClienteRepository clienteRepository;
    private final IOrdemDeServicoRepository ordemDeServicoRepository;
    private final IVeiculoRepository veiculoRepository;

    //  CRIAR CLIENTE - POST
    public void criarCliente(ClienteDto clienteDto) throws BadRequestException {
        ClienteEntity cliente = clienteRepository.findByEmail(clienteDto.getEmail()).orElse(null);
        if (cliente != null) {
            throw new BadRequestException("Email ja cadastrado");
        }
        cliente = ClienteEntity.builder()
                .email(clienteDto.getEmail())
                .nome(clienteDto.getNome())
                .build();
        clienteRepository.save(cliente);
    }

    //  BUSCAR POR ID - GET
    public ClienteEntity findClienteById(UUID id) throws NotFoundException {
        return clienteRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

    //  BUSCAR POR EMAIL - GET
    public ClienteEntity findClienteByEmail(String email) throws NotFoundException {
        return clienteRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
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

    public List<ClienteEntity> findAll(){
        return clienteRepository.findAll();
    }
}
