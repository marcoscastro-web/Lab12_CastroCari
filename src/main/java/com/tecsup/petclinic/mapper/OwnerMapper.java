package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.entities.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OwnerMapper {
    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);

    Owner mapToEntity(OwnerDTO dto);
    OwnerDTO mapToDto(Owner entity);
    List<OwnerDTO> mapToDtoList(List<Owner> ownerList);
}