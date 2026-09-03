package br.com.daSilva.apiDeOficinaMecanica.controller;

import br.com.daSilva.apiDeOficinaMecanica.dto.request.ServicoRequestDto;
import br.com.daSilva.apiDeOficinaMecanica.dto.response.ServicoResponseDto;
import br.com.daSilva.apiDeOficinaMecanica.service.ServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/servico")
@Validated
public class ServicoController {
    private final ServicoService servicoService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarServico(@Valid @RequestBody ServicoRequestDto dto) {
        servicoService.criarServico(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ServicoResponseDto> findServico() {
        return servicoService.findAllService();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServicoResponseDto findByIdServico(@Valid @PathVariable UUID id) {
        return servicoService.findServiceId(id);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServico(@Valid @PathVariable UUID id) {
        servicoService.deleteService(id);
    }
}
