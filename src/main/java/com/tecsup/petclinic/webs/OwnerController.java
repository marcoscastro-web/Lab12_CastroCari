package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import com.tecsup.petclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owners")
    public ResponseEntity<List<OwnerDTO>> findAll() {
        return ResponseEntity.ok(ownerService.findAll());
    }

    @GetMapping("/owners/{id}")
    public ResponseEntity<OwnerDTO> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ownerService.findById(id));
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/owners")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OwnerDTO> create(@RequestBody OwnerDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.create(dto));
    }

    @PutMapping("/owners/{id}")
    public ResponseEntity<OwnerDTO> update(@RequestBody OwnerDTO dto, @PathVariable Long id) {
        try {
            OwnerDTO existing = ownerService.findById(id);
            existing.setFirstName(dto.getFirstName());
            existing.setLastName(dto.getLastName());
            existing.setAddress(dto.getAddress());
            existing.setCity(dto.getCity());
            existing.setTelephone(dto.getTelephone());
            return ResponseEntity.ok(ownerService.update(existing));
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}