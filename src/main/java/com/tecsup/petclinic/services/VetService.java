package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import java.util.List;

public interface VetService {
    VetDTO create(VetDTO dto);
    VetDTO update(VetDTO dto);
    void delete(Integer id) throws VetNotFoundException;
    VetDTO findById(Integer id) throws VetNotFoundException;
    List<VetDTO> findAll();
}