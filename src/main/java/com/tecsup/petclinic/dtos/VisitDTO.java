package com.tecsup.petclinic.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitDTO {
    private Long id;
    private LocalDate visitDate;
    private String description;
    private Integer petId; // Usamos el ID de la mascota para facilitar el envío JSON
}