package br.petersonaraujo.livraria.controller.autor.mapper;

import br.petersonaraujo.livraria.controller.autor.dto.AutorCriadoResponse;
import br.petersonaraujo.livraria.controller.autor.dto.AutorRequest;
import br.petersonaraujo.livraria.controller.autor.dto.AutorResponse;
import br.petersonaraujo.livraria.controller.livro.dto.LivroRequest;
import br.petersonaraujo.livraria.controller.livro.dto.LivroResponse;
import br.petersonaraujo.livraria.model.autor.AutorEntity;
import br.petersonaraujo.livraria.model.livro.LivroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    AutorMapper MAPPER = Mappers.getMapper(AutorMapper.class);

    AutorEntity toEntity(AutorRequest autorRequest);
    AutorResponse toResponse(AutorEntity autorEntity);
    AutorCriadoResponse toCreatedResponse(AutorEntity autorEntity);
    AutorEntity toEntity(LivroRequest.AutorRequest autorRequest);

    @Mapping(source = "editora.nome", target = "editora")
    LivroResponse toResponse(LivroEntity livroEntity);
}
