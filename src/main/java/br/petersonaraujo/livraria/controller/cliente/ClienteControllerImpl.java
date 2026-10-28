package br.petersonaraujo.livraria.controller.cliente;

import br.petersonaraujo.livraria.controller.cliente.dto.ClienteDTO;
import br.petersonaraujo.livraria.controller.cliente.mapper.ClienteMapper;
import br.petersonaraujo.livraria.service.cliente.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteControllerImpl implements IClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper mapper;

    @Override
    @PostMapping
    public ResponseEntity<ClienteDTO> salvarCliente(@Valid @RequestBody ClienteDTO dto) {
        var salvo = clienteService.salvarCliente(mapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(salvo));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDTO(clienteService.buscarClientePorId(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<ClienteDTO> buscarClientePorNome(@RequestParam String nome) {
        return ResponseEntity.ok(mapper.toDTO(clienteService.buscarClientePorNome(nome)));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarClientePorId(@PathVariable Long id,
                                                            @Valid @RequestBody ClienteDTO dto) {
        var atualizado = clienteService.atualizarClientePorId(id, mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDTO(atualizado));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerClientePorId(@PathVariable Long id) {
        clienteService.removerClientePorId(id);
        return ResponseEntity.noContent().build();
    }
}