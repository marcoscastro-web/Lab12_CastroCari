package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.PetTypeDTO;
import com.tecsup.petclinic.exceptions.PetTypeNotFoundException;
import java.util.List;

public interface PetTypeService {
    PetTypeDTO create(PetTypeDTO dto);
    PetTypeDTO update(PetTypeDTO dto);
    void delete(Integer id) throws PetTypeNotFoundException;
    PetTypeDTO findById(Integer id) throws PetTypeNotFoundException;
    List<PetTypeDTO> findAll();
}