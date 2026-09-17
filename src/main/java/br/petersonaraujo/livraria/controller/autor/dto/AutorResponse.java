package br.petersonaraujo.livraria.controller.autor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public record AutorResponse (
        Long id,
        String nome,
        String biografia
) {
}
