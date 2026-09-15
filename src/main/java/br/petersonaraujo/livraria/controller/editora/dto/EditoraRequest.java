package br.petersonaraujo.livraria.controller.editora.dto;

public record EditoraRequest(
        String nome,
        String cnpj,
        String email
) {
}
