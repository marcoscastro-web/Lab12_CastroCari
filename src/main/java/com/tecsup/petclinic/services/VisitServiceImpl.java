package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import com.tecsup.petclinic.mapper.VisitMapper;
import com.tecsup.petclinic.repositories.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;
    private final VisitMapper visitMapper;

    @Override
    public VisitDTO create(VisitDTO dto) {
        Visit entity = visitRepository.save(visitMapper.mapToEntity(dto));
        return visitMapper.mapToDto(entity);
    }

    @Override
    public VisitDTO update(VisitDTO dto) {
        Visit entity = visitRepository.save(visitMapper.mapToEntity(dto));
        return visitMapper.mapToDto(entity);
    }

    @Override
    public void delete(Long id) throws VisitNotFoundException {
        VisitDTO dto = findById(id);
        visitRepository.delete(visitMapper.mapToEntity(dto));
    }

    @Override
    public VisitDTO findById(Long id) throws VisitNotFoundException {
        Optional<Visit> entity = visitRepository.findById(id);
        if (!entity.isPresent()) {
            throw new VisitNotFoundException("Record not found...!");
        }
        return visitMapper.mapToDto(entity.get());
    }

    @Override
    public List<VisitDTO> findByPetId(Integer petId) {
        return visitRepository.findByPetId(petId).stream()
                .map(visitMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<VisitDTO> findAll() {
        return visitRepository.findAll().stream().map(visitMapper::mapToDto).collect(Collectors.toList());
    }
}