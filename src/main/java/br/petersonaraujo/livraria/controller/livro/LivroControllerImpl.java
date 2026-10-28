package br.petersonaraujo.livraria.controller.livro;

import br.petersonaraujo.livraria.controller.livro.dto.LivroRequest;
import br.petersonaraujo.livraria.controller.livro.dto.LivroResponse;
import br.petersonaraujo.livraria.controller.livro.mapper.LivroMapper;
import br.petersonaraujo.livraria.service.livro.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
@RequiredArgsConstructor
public class LivroControllerImpl implements ILivroController {

    private final LivroService livroService;
    private final LivroMapper mapper;

    @Override
    @PostMapping
    public ResponseEntity<LivroResponse> salvarLivro(@RequestBody @Valid LivroRequest livroRequest) {
        var livroSalvo = livroService.salvarLivro(mapper.toModel(livroRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(livroSalvo));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<LivroResponse>> listarLivros() {
        return ResponseEntity.ok(
                livroService.listarLivros().stream()
                        .map(mapper::toResponse)
                        .toList()
        );
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> buscarLivroPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.toResponse(livroService.buscarLivroPorId(id)));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> atualizarLivroPorId(@PathVariable Long id,
                                                             @RequestBody @Valid LivroRequest livroRequest) {
        return ResponseEntity.ok().body(mapper.toResponse(livroService.atualizarLivroPorId(id, livroRequest)));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivroPorId(@PathVariable Long id) {
        livroService.deletarLivroPorId(id);

        return ResponseEntity.noContent().build();
    }
}