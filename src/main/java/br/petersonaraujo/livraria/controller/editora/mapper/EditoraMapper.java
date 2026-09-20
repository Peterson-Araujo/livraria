package br.petersonaraujo.livraria.controller.editora.mapper;

import br.petersonaraujo.livraria.controller.editora.dto.EditoraRequest;
import br.petersonaraujo.livraria.controller.editora.dto.EditoraResponse;
import br.petersonaraujo.livraria.controller.livro.dto.LivroRequest;
import br.petersonaraujo.livraria.model.editora.EditoraEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EditoraMapper {

    EditoraResponse toResponse(EditoraEntity editoraEntity);

    EditoraEntity toModel(EditoraRequest editoraRequest);

    EditoraEntity toModel(LivroRequest.EditoraRequest editora);
}
