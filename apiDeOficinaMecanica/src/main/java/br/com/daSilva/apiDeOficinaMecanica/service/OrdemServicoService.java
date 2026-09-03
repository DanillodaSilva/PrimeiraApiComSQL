package br.com.daSilva.apiDeOficinaMecanica.service;

import br.com.daSilva.apiDeOficinaMecanica.databse.model.ClienteEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.model.OrdemDeServicoEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.model.ServicoEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.model.VeiculoEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IClienteRepository;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IOrdemDeServicoRepository;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IServicoRepository;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IVeiculoRepository;
import br.com.daSilva.apiDeOficinaMecanica.dto.request.OrdemDeServicoDto;
import br.com.daSilva.apiDeOficinaMecanica.dto.response.OrdemServicoResponseDto;
import br.com.daSilva.apiDeOficinaMecanica.dto.response.ServicoResponseDto;
import br.com.daSilva.apiDeOficinaMecanica.dto.response.VeiculoResponseDto;
import br.com.daSilva.apiDeOficinaMecanica.exception.BadRequestException;
import br.com.daSilva.apiDeOficinaMecanica.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdemServicoService {

    private final IOrdemDeServicoRepository ordemDeServicoRepository;
    private final IClienteRepository clienteRepository;
    private final IServicoRepository servicoRepository;
    private final IVeiculoRepository veiculoRepository;

    public void criarOrdemServico(OrdemDeServicoDto dto) {
        OrdemDeServicoEntity ordemDeServico = ordemDeServicoRepository
                .findById(dto.getVeiculoId()).orElse(null);
        if (ordemDeServico != null) {
            throw new BadRequestException("Ordem de serviço ja cadastrada");
        }
        ClienteEntity cliente = clienteRepository
                .findById(dto.getClienteId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        VeiculoEntity veiculo = veiculoRepository
                .findById(dto.getVeiculoId())
                .orElseThrow(() -> new NotFoundException("Veiculo não encontrado"));

        Set<ServicoEntity> servicoEntities = new HashSet<>(servicoRepository
                .findAllById(dto.getServicoIds()));

        if (servicoEntities.size() != dto.getServicoIds().size()) {
            throw new BadRequestException("Um ou mais serviços não foram encontrados");
        }
        ordemDeServico = OrdemDeServicoEntity
                .builder()
                .descricao(dto.getDescricao())
                .cliente(cliente)
                .servico(servicoEntities)
                .veiculo(veiculo).build();
        ordemDeServicoRepository.save(ordemDeServico);
    }

    public List<OrdemServicoResponseDto> findAllOrdem() {
        List<OrdemDeServicoEntity> ordens =
                ordemDeServicoRepository.findAll();
        return ordens.stream()
                .map(o -> {
                    VeiculoResponseDto veiculoResponse =
                            new VeiculoResponseDto(
                                    o.getVeiculo().getId(),
                                    o.getVeiculo().getModelo(),
                                    o.getVeiculo().getPlaca(),
                                    o.getVeiculo().getCliente().getId()
                            );
                    Set<ServicoResponseDto> servicosResponse =
                            o.getServico()
                                    .stream()
                                    .map(s -> new ServicoResponseDto(
                                            s.getId(),
                                            s.getNome(),
                                            s.getPreco()
                                    ))
                                    .collect(Collectors.toSet());
                    return new OrdemServicoResponseDto(
                            o.getDescricao(),
                            o.getCliente().getId(),
                            veiculoResponse,
                            servicosResponse,
                            o.getId()
                    );
                })
                .toList();
    }

    public OrdemServicoResponseDto findByIdOrdem(UUID id) {
        OrdemDeServicoEntity ordem = ordemDeServicoRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Ordem de serviço não encontrada"));

        VeiculoResponseDto veiculo = new VeiculoResponseDto(
                ordem.getVeiculo().getId(),
                ordem.getVeiculo().getModelo(),
                ordem.getVeiculo().getPlaca(),
                ordem.getVeiculo().getCliente().getId()
        );

        return new OrdemServicoResponseDto(
                ordem.getDescricao(),
                ordem.getCliente().getId(),
                veiculo,
                ordem.getServico().stream().map(s -> new ServicoResponseDto(
                        s.getId(),
                        s.getNome(),
                        s.getPreco()
                )).collect(Collectors.toSet()),
                ordem.getId()
        );
    }

    public void deleteOrdem(UUID id) {
        OrdemDeServicoEntity ordem = ordemDeServicoRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Ordem de serviço não encontrada"));

        ordemDeServicoRepository.deleteById(id);
    }
}
