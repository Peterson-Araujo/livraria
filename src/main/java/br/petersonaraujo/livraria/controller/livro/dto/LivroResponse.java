package br.petersonaraujo.livraria.controller.livro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
        List<AutorResponse> autor
) {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AutorResponse {
        private Long id;
        private String nome;
        private String biografia;
    }
}
