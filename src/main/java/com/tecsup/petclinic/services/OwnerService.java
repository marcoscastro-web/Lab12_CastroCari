package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import java.util.List;

public interface OwnerService {
    OwnerDTO create(OwnerDTO dto);
    OwnerDTO update(OwnerDTO dto);
    void delete(Long id) throws OwnerNotFoundException;
    OwnerDTO findById(Long id) throws OwnerNotFoundException;
    List<OwnerDTO> findAll();
}