package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.entities.Vet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VetMapper {
    VetMapper INSTANCE = Mappers.getMapper(VetMapper.class);

    Vet mapToEntity(VetDTO dto);
    VetDTO mapToDto(Vet entity);
    List<VetDTO> mapToDtoList(List<Vet> vetList);
}