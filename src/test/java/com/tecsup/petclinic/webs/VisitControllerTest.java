package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.dtos.VisitDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VisitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Escenario 1: Registrar una visita válida (asociada a una mascota)
    @Test
    public void testCreateVisit() throws Exception {
        VisitDTO newVisit = VisitDTO.builder()
                .visitDate(LocalDate.now())
                .description("Chequeo general de rutina")
                .petId(1) // Asumiendo que la mascota con ID 1 existe
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/visits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newVisit)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value("Chequeo general de rutina"))
                .andExpect(jsonPath("$.petId").value(1));
    }

    // Escenario 2: Consultar las visitas de una mascota
    @Test
    public void testFindVisitsByPetId() throws Exception {
        int PET_ID = 1;

        this.mockMvc.perform(MockMvcRequestBuilders.get("/visits/pet/" + PET_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    // Escenario 3: Comprobar comportamiento ante un ID inexistente
    @Test
    public void testFindVisitNotFound() throws Exception {
        long NON_EXISTENT_ID = 9999;

        this.mockMvc.perform(MockMvcRequestBuilders.get("/visits/" + NON_EXISTENT_ID))
                .andExpect(status().isNotFound());
    }
}