package br.petersonaraujo.livraria.controller.editora.dto;

public record EditoraResponse(
        Long id,
        String nome,
        String cnpj,
        String email
) {
}
