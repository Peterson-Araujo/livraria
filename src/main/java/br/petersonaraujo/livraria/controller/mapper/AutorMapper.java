package br.petersonaraujo.livraria.controller.mapper;

import br.petersonaraujo.livraria.controller.dto.AutorRequest;
import br.petersonaraujo.livraria.controller.dto.AutorResponse;
import br.petersonaraujo.livraria.controller.dto.LivroResponse;
import br.petersonaraujo.livraria.model.AutorEntity;
import br.petersonaraujo.livraria.model.LivroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    AutorMapper MAPPER = Mappers.getMapper(AutorMapper.class);

    AutorEntity toEntity(AutorRequest autorRequest);
    AutorResponse toResponse(AutorEntity autorEntity);

    @Mapping(source = "editora.nome", target = "editora")
    LivroResponse toResponse(LivroEntity livroEntity);
}
