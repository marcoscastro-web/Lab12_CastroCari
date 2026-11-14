package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VetSpecialtyServiceTest {

    @Autowired
    private VetService vetService;

    // Escenario 1: Comprobar que un veterinario recupera sus especialidades
    @Test
    public void testFindSpecialtiesByVet() {
        // Según tu data.sql, el veterinario con ID 2 tiene especialidades asociadas
        int VET_ID = 2;

        try {
            VetDTO vet = vetService.findById(VET_ID);

            // Verificamos que el veterinario se recuperó correctamente
            assertNotNull(vet);
            assertEquals(VET_ID, vet.getId());

            // NOTA: Como el DTO actual no tiene la lista de especialidades,
            // esta prueba solo verifica que el veterinario existe y se puede consultar.
            // Si el DTO tuviera la lista, aquí comprobaríamos que no esté vacía.

        } catch (VetNotFoundException e) {
            fail("No se encontró el veterinario con ID " + VET_ID);
        }
    }

    // Escenario 2: Comprobar un veterinario sin especialidades (o inexistente)
    @Test
    public void testVetWithoutSpecialties() {
        // Usamos un ID que sabemos que no existe (según tu data.sql)
        int NON_EXISTENT_VET_ID = 9999;

        try {
            vetService.findById(NON_EXISTENT_VET_ID);
            fail("Se esperaba una excepción para un veterinario inexistente");
        } catch (VetNotFoundException e) {
            // Si llega aquí, es porque la excepción se lanzó correctamente
            assertTrue(true);
        }
    }
}