package br.petersonaraujo.livraria.controller.livro;

import br.petersonaraujo.livraria.controller.livro.dto.LivroRequest;
import br.petersonaraujo.livraria.controller.livro.dto.LivroResponse;
import br.petersonaraujo.livraria.controller.livro.mapper.LivroMapper;
import br.petersonaraujo.livraria.service.livro.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<LivroResponse> salvarLivro(@RequestBody LivroRequest livroRequest) {
        var livroSalvo = livroService.salvarLivro(mapper.toModel(livroRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(livroSalvo));
    }

    @GetMapping
    public ResponseEntity<List<LivroResponse>> listarLivros() {
        return ResponseEntity.ok(
                livroService.listarLivros().stream()
                        .map(mapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> buscarLivroPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.toResponse(livroService.buscarLivroPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> atualizarLivroPorId(@PathVariable Long id, @RequestBody LivroRequest livroRequest) {
        return ResponseEntity.ok().body(mapper.toResponse(livroService.atualizarLivroPorId(id, livroRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LivroResponse> deletarLivroPorId(@PathVariable Long id) {

        livroService.deletarLivroPorId(id);

        return ResponseEntity.noContent().build();
    }
}
