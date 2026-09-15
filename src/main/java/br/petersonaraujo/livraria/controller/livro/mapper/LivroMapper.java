package br.petersonaraujo.livraria.controller.livro.mapper;

import br.petersonaraujo.livraria.controller.livro.dto.LivroRequest;
import br.petersonaraujo.livraria.controller.livro.dto.LivroResponse;
import br.petersonaraujo.livraria.model.livro.LivroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    LivroMapper MAPPER = Mappers.getMapper(LivroMapper.class);

    @Mapping(source = "editora.nome", target = "editora")
    LivroResponse toResponse(LivroEntity livroEntity);

    LivroEntity toModel(LivroRequest livroRequest);
}
