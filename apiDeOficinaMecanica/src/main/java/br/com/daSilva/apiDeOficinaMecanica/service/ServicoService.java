package br.com.daSilva.apiDeOficinaMecanica.service;

import br.com.daSilva.apiDeOficinaMecanica.databse.model.ServicoEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IServicoRepository;
import br.com.daSilva.apiDeOficinaMecanica.databse.repository.IVeiculoRepository;
import br.com.daSilva.apiDeOficinaMecanica.dto.request.ServicoDto;
import br.com.daSilva.apiDeOficinaMecanica.dto.response.ServicoResponseDto;
import br.com.daSilva.apiDeOficinaMecanica.exception.BadRequestExceptionn;
import br.com.daSilva.apiDeOficinaMecanica.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServicoService {
    private final IServicoRepository servicoRepository;
    private final IVeiculoRepository veiculoRepository;

    public void criarServico(ServicoDto dto){

        ServicoEntity servicoEntity = servicoRepository
                .findByNome(dto.getNome())
                .orElse(null);
        if (servicoEntity != null){
            throw new BadRequestExceptionn("Serviço já cadastrado");
        }

        servicoEntity = ServicoEntity.builder()
                .nome(dto.getNome())
                .preco(dto.getPreco())
                .build();
        servicoRepository.save(servicoEntity);
    }

    public List<ServicoResponseDto> findAllService(){
        List<ServicoEntity> servicoEntity = servicoRepository.findAll();
        return  servicoEntity
                .stream()
                .map(s->new ServicoResponseDto(
                        s.getId(),
                        s.getNome(),
                        s.getPreco()
                )).toList();
    }
    public ServicoResponseDto findServiceId(UUID id){
        ServicoEntity servicoEntity = servicoRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Serviço não encontrado"));

        return new ServicoResponseDto(
                servicoEntity.getId(),
                servicoEntity.getNome(),
                servicoEntity.getPreco()
        );
    }
    public void deleteService(UUID id){
        ServicoEntity servicoEntity = servicoRepository
                .findById(id)
                .orElseThrow(()->new NotFoundException("Serviço não encontrado"));

        servicoRepository.deleteById(servicoEntity.getId());
    }
}
