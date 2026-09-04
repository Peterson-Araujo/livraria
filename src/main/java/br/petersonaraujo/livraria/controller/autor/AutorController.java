package br.petersonaraujo.livraria.controller.autor;

import br.petersonaraujo.livraria.controller.autor.dto.AutorCriadoResponse;
import br.petersonaraujo.livraria.controller.autor.dto.AutorRequest;
import br.petersonaraujo.livraria.controller.autor.dto.AutorResponse;
import br.petersonaraujo.livraria.controller.autor.mapper.AutorMapper;
import br.petersonaraujo.livraria.service.autor.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;
    private final AutorMapper mapper;

    @PostMapping
    public ResponseEntity<AutorCriadoResponse> salvarAutor(@RequestBody @Valid AutorRequest autorRequest) {
        var autorSalvo = autorService.salvarAutor(mapper.toEntity(autorRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toCreatedResponse(autorSalvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponse> buscarAutorPorId(@PathVariable Long id){
        return ResponseEntity.ok(mapper.toResponse(autorService.buscarAutorPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<AutorResponse>> listarAutores(@RequestParam String nome){
        var autores = autorService.listarAutoresPorNome(nome);

        return ResponseEntity.ok(
                autores.stream()
                        .map(mapper::toResponse)
                        .toList()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponse> atualizarAutor(@PathVariable Long id, @RequestBody @Valid AutorRequest autorRequest){
        var autorAtualizado = autorService.atualizarAutorPorId(id, mapper.toEntity(autorRequest));

        return ResponseEntity.ok().body(mapper.toResponse(autorAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAutor(@PathVariable Long id){
        autorService.removerAutorPorId(id);
        return ResponseEntity.noContent().build();
    }
}
