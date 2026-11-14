package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import com.tecsup.petclinic.mapper.SpecialtyMapper;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper;

    @Override
    public SpecialtyDTO create(SpecialtyDTO dto) {
        Specialty entity = specialtyRepository.save(specialtyMapper.mapToEntity(dto));
        return specialtyMapper.mapToDto(entity);
    }

    @Override
    public SpecialtyDTO update(SpecialtyDTO dto) {
        Specialty entity = specialtyRepository.save(specialtyMapper.mapToEntity(dto));
        return specialtyMapper.mapToDto(entity);
    }

    @Override
    public void delete(Integer id) throws SpecialtyNotFoundException {
        SpecialtyDTO dto = findById(id);
        specialtyRepository.delete(specialtyMapper.mapToEntity(dto));
    }

    @Override
    public SpecialtyDTO findById(Integer id) throws SpecialtyNotFoundException {
        Optional<Specialty> entity = specialtyRepository.findById(id);
        if (!entity.isPresent()) {
            throw new SpecialtyNotFoundException("Record not found...!");
        }
        return specialtyMapper.mapToDto(entity.get());
    }

    @Override
    public List<SpecialtyDTO> findAll() {
        return specialtyRepository.findAll().stream().map(specialtyMapper::mapToDto).collect(Collectors.toList());
    }
}