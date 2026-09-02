package br.com.daSilva.apiDeOficinaMecanica.controller;

import br.com.daSilva.apiDeOficinaMecanica.databse.model.ClienteEntity;
import br.com.daSilva.apiDeOficinaMecanica.databse.model.VeiculoEntity;
import br.com.daSilva.apiDeOficinaMecanica.dto.ClienteDto;
import br.com.daSilva.apiDeOficinaMecanica.dto.VeiculoDto;
import br.com.daSilva.apiDeOficinaMecanica.exception.NotFoundException;
import br.com.daSilva.apiDeOficinaMecanica.service.ClienteService;
import br.com.daSilva.apiDeOficinaMecanica.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/veiculo")
public class VeiculoController {
    private final VeiculoService veiculoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarVeiculo(@Valid @RequestBody VeiculoDto dto){
        veiculoService.criarVeiculo(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VeiculoEntity> findAllVeiculo(){
        return veiculoService.findVeiculo();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VeiculoEntity findClienteById(@Valid @PathVariable UUID id) throws NotFoundException {
        return veiculoService.findVeiculoById(id);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable UUID id){
        veiculoService.deleteVeiculo(id);
    }
}
