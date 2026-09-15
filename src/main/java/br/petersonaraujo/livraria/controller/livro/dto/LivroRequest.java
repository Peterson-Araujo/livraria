package br.petersonaraujo.livraria.controller.livro.dto;

import br.petersonaraujo.livraria.controller.editora.dto.EditoraRequest;
import br.petersonaraujo.livraria.model.autor.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

public record LivroRequest(
        String isbn,
        String titulo,
        String descricao,
        BigDecimal preco,
        int estoque,
        int anoPublicacao,
        Categoria categoria,
        EditoraRequest editora,
        List<AutorRequest> autor
) {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AutorRequest{
        private Long id;
    }
}
