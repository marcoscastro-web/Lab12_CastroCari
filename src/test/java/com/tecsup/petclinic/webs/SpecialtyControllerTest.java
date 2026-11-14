package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.dtos.SpecialtyDTO;
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
public class SpecialtyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Escenario 1: Consultar el listado de especialidades existentes
    @Test
    public void testFindAllSpecialties() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/specialties"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    // Escenario 2: Registrar una nueva especialidad (y verificar persistencia)
    @Test
    public void testCreateSpecialty() throws Exception {
        SpecialtyDTO newSpecialty = SpecialtyDTO.builder()
                .name("Cardiología")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/specialties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSpecialty)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Cardiología"));
    }
}