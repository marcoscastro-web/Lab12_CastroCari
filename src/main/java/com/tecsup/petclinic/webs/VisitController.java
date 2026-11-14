package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import com.tecsup.petclinic.services.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/visits")
    public ResponseEntity<List<VisitDTO>> findAll() {
        return ResponseEntity.ok(visitService.findAll());
    }

    @GetMapping("/visits/{id}")
    public ResponseEntity<VisitDTO> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(visitService.findById(id));
        } catch (VisitNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/visits")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VisitDTO> create(@RequestBody VisitDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(visitService.create(dto));
    }

    // Escenario 2: Consultar visitas por mascota
    @GetMapping("/visits/pet/{petId}")
    public ResponseEntity<List<VisitDTO>> findByPetId(@PathVariable Integer petId) {
        return ResponseEntity.ok(visitService.findByPetId(petId));
    }
}