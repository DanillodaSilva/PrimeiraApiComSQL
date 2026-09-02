package br.com.daSilva.apiDeOficinaMecanica.controller;

import br.com.daSilva.apiDeOficinaMecanica.dto.request.ClienteDto;
import br.com.daSilva.apiDeOficinaMecanica.dto.response.ClienteResponseDto;
import br.com.daSilva.apiDeOficinaMecanica.exception.NotFoundException;
import br.com.daSilva.apiDeOficinaMecanica.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarCliente(@Valid @RequestBody ClienteDto dto){
        clienteService.criarCliente(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteResponseDto> findAllCliente(){
        return clienteService.findAll();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDto findClienteById(@Valid @PathVariable UUID id) throws NotFoundException {
        return clienteService.findClienteById(id);
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDto findClienteByEmail(@Valid @PathVariable String email) throws NotFoundException {
        return clienteService.findClienteByEmail(email);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable UUID id){
        clienteService.deletarClientePeloId(id);
    }
}
