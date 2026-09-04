package br.petersonaraujo.livraria.controller.dto;

import java.util.List;

public record AutorResponse(
        Long id,
        String nome,
        String biografia,
        List<LivroResponse> livros
) {
}
