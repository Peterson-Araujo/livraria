package br.petersonaraujo.livraria.controller.mapper;

import br.petersonaraujo.livraria.controller.dto.LivroResponse;
import br.petersonaraujo.livraria.model.LivroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    LivroMapper MAPPER = Mappers.getMapper(LivroMapper.class);

    @Mapping(target = "editora", source = "editora.email")
    LivroResponse toResponse(LivroEntity livroEntity);
}
