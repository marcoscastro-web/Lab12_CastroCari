package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.dtos.PetTypeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PetTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Escenario 1: Consultar el catálogo completo de tipos
    @Test
    public void testFindAllTypes() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    // Escenario 2: Registrar un nuevo tipo (y verificar persistencia)
    @Test
    public void testCreateType() throws Exception {
        PetTypeDTO newType = PetTypeDTO.builder()
                .name("Conejo")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newType)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Conejo"));
    }
}