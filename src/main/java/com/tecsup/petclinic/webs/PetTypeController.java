package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.PetTypeDTO;
import com.tecsup.petclinic.exceptions.PetTypeNotFoundException;
import com.tecsup.petclinic.services.PetTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PetTypeController {

    private final PetTypeService petTypeService;

    public PetTypeController(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @GetMapping("/types")
    public ResponseEntity<List<PetTypeDTO>> findAll() {
        return ResponseEntity.ok(petTypeService.findAll());
    }

    @GetMapping("/types/{id}")
    public ResponseEntity<PetTypeDTO> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(petTypeService.findById(id));
        } catch (PetTypeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/types")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PetTypeDTO> create(@RequestBody PetTypeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petTypeService.create(dto));
    }
}