package br.petersonaraujo.livraria.controller.livro;

import br.petersonaraujo.livraria.controller.livro.dto.LivroRequest;
import br.petersonaraujo.livraria.controller.livro.dto.LivroResponse;
import br.petersonaraujo.livraria.controller.livro.mapper.LivroMapper;
import br.petersonaraujo.livraria.service.livro.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
