package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.PetTypeDTO;
import com.tecsup.petclinic.entities.PetType;
import com.tecsup.petclinic.exceptions.PetTypeNotFoundException;
import com.tecsup.petclinic.mapper.PetTypeMapper;
import com.tecsup.petclinic.repositories.PetTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetTypeServiceImpl implements PetTypeService {

    private final PetTypeRepository petTypeRepository;
    private final PetTypeMapper petTypeMapper;

    @Override
    public PetTypeDTO create(PetTypeDTO dto) {
        PetType entity = petTypeRepository.save(petTypeMapper.mapToEntity(dto));
        return petTypeMapper.mapToDto(entity);
    }

    @Override
    public PetTypeDTO update(PetTypeDTO dto) {
        PetType entity = petTypeRepository.save(petTypeMapper.mapToEntity(dto));
        return petTypeMapper.mapToDto(entity);
    }

    @Override
    public void delete(Integer id) throws PetTypeNotFoundException {
        PetTypeDTO dto = findById(id);
        petTypeRepository.delete(petTypeMapper.mapToEntity(dto));
    }

    @Override
    public PetTypeDTO findById(Integer id) throws PetTypeNotFoundException {
        Optional<PetType> entity = petTypeRepository.findById(id);
        if (!entity.isPresent()) {
            throw new PetTypeNotFoundException("Record not found...!");
        }
        return petTypeMapper.mapToDto(entity.get());
    }

    @Override
    public List<PetTypeDTO> findAll() {
        return petTypeRepository.findAll().stream().map(petTypeMapper::mapToDto).collect(Collectors.toList());
    }
}