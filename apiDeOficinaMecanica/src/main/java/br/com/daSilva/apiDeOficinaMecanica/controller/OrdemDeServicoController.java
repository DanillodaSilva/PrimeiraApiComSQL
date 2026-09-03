package br.com.daSilva.apiDeOficinaMecanica.controller;

import br.com.daSilva.apiDeOficinaMecanica.dto.request.OrdemDeServicoRequestDto;
import br.com.daSilva.apiDeOficinaMecanica.dto.response.OrdemServicoResponseDto;
import br.com.daSilva.apiDeOficinaMecanica.service.OrdemServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/ordem")
public class OrdemDeServicoController {
    private final OrdemServicoService ordemServicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarOrdemServico(@RequestBody OrdemDeServicoRequestDto dto) {
        ordemServicoService.criarOrdemServico(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrdemServicoResponseDto> findOrdemServico() {
        return ordemServicoService.findAllOrdem();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrdemServicoResponseDto findIdOrdemServico(@PathVariable UUID id) {
        return ordemServicoService.findByIdOrdem(id);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrdemServicoById(@PathVariable UUID id) {
        ordemServicoService.deleteOrdem(id);
    }

}
