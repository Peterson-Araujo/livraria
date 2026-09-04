package br.petersonaraujo.livraria.controller.autor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutorRequest(

        @Size(min = 1, max = 255, message = "O nome não deve estar vazio")
        @NotBlank
        String nome,

        @Size(min = 1, max = 255, message = "A biografia deve estar entre 1 e 255 caracteres")
        String biografia
) {
}
