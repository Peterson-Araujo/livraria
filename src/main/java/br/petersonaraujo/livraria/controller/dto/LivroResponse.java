package br.petersonaraujo.livraria.controller.dto;

import java.math.BigDecimal;
import java.util.List;

public record LivroResponse(
        Long id,
        String isbn,
        String titulo,
        String descricao,
        BigDecimal preco,
        int estoque,
        int anoPublicacao,
        String editora,
        String categoria,
        List<AutorResponse> autores
) {
}
