package br.petersonaraujo.livraria.controller.pedido.mapper;

import br.petersonaraujo.livraria.controller.pedido.dto.ClienteDTO;
import br.petersonaraujo.livraria.model.pedido.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    ClienteEntity toEntity(ClienteDTO dto);

    ClienteDTO toDTO(ClienteEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    void updateEntity(ClienteDTO dto, @MappingTarget ClienteEntity entity);
}