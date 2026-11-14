package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.dtos.PetTypeDTO;
import com.tecsup.petclinic.entities.PetType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetTypeMapper {
    PetTypeMapper INSTANCE = Mappers.getMapper(PetTypeMapper.class);

    PetType mapToEntity(PetTypeDTO dto);
    PetTypeDTO mapToDto(PetType entity);
    List<PetTypeDTO> mapToDtoList(List<PetType> typeList);
}