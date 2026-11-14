package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import com.tecsup.petclinic.mapper.OwnerMapper;
import com.tecsup.petclinic.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Override
    public OwnerDTO create(OwnerDTO dto) {
        Owner entity = ownerRepository.save(ownerMapper.mapToEntity(dto));
        return ownerMapper.mapToDto(entity);
    }

    @Override
    public OwnerDTO update(OwnerDTO dto) {
        Owner entity = ownerRepository.save(ownerMapper.mapToEntity(dto));
        return ownerMapper.mapToDto(entity);
    }

    @Override
    public void delete(Long id) throws OwnerNotFoundException {
        OwnerDTO owner = findById(id);
        ownerRepository.delete(ownerMapper.mapToEntity(owner));
    }

    @Override
    public OwnerDTO findById(Long id) throws OwnerNotFoundException {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (!owner.isPresent()) {
            throw new OwnerNotFoundException("Record not found...!");
        }
        return ownerMapper.mapToDto(owner.get());
    }

    @Override
    public List<OwnerDTO> findAll() {
        return ownerRepository.findAll().stream().map(ownerMapper::mapToDto).collect(Collectors.toList());
    }
}