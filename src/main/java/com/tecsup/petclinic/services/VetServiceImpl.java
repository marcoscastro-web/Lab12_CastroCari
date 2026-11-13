package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import com.tecsup.petclinic.mapper.VetMapper;
import com.tecsup.petclinic.repositories.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;
    private final VetMapper vetMapper;

    @Override
    public VetDTO create(VetDTO dto) {
        Vet entity = vetRepository.save(vetMapper.mapToEntity(dto));
        return vetMapper.mapToDto(entity);
    }

    @Override
    public VetDTO update(VetDTO dto) {
        Vet entity = vetRepository.save(vetMapper.mapToEntity(dto));
        return vetMapper.mapToDto(entity);
    }

    @Override
    public void delete(Integer id) throws VetNotFoundException {
        VetDTO vet = findById(id);
        vetRepository.delete(vetMapper.mapToEntity(vet));
    }

    @Override
    public VetDTO findById(Integer id) throws VetNotFoundException {
        Optional<Vet> vet = vetRepository.findById(id);
        if (!vet.isPresent()) {
            throw new VetNotFoundException("Record not found...!");
        }
        return vetMapper.mapToDto(vet.get());
    }

    @Override
    public List<VetDTO> findAll() {
        return vetRepository.findAll().stream().map(vetMapper::mapToDto).collect(Collectors.toList());
    }
}