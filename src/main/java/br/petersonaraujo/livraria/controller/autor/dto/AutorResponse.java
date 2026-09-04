package br.petersonaraujo.livraria.controller.autor.dto;

import br.petersonaraujo.livraria.controller.livro.dto.LivroResponse;

import java.util.List;

public record AutorResponse(
        Long id,
        String nome,
        String biografia,
        List<LivroResponse> livros
) {
}
