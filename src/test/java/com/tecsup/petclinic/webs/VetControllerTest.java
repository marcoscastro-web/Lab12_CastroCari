package com.tecsup.petclinic.webs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Escenario 1: Consultar el listado completo
    @Test
    public void testFindAllVets() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/vets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    // Escenario 2: Consultar un veterinario existente por su ID
    @Test
    public void testFindVetById() throws Exception {
        // Verifica en tu data.sql qué ID de vet existe, usualmente es el 1
        int VET_ID = 1;

        this.mockMvc.perform(MockMvcRequestBuilders.get("/vets/" + VET_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(VET_ID));
    }
}