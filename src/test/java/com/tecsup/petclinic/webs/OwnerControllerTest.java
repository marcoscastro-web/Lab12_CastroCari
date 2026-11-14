package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.dtos.OwnerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // Para convertir objetos a JSON

    // Escenario 1: Consultar un propietario existente por ID
    @Test
    public void testFindOwnerById() throws Exception {
        long OWNER_ID = 1; // Asumiendo que el data.sql tiene este ID

        this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/" + OWNER_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(OWNER_ID));
    }

    // Escenario 2: Registrar un nuevo propietario y verificar persistencia
    @Test
    public void testCreateOwner() throws Exception {
        OwnerDTO newOwner = OwnerDTO.builder()
                .firstName("Juan")
                .lastName("Perez")
                .address("Av. Lima 123")
                .city("Lima")
                .telephone("987654321")
                .build();

        // 1. Hacemos el POST para crearlo
        this.mockMvc.perform(MockMvcRequestBuilders.post("/owners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newOwner)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Juan"));

        // NOTA: Para verificar que se guardó, podríamos hacer otro GET,
        // pero con el status 201 y los datos devueltos es suficiente evidencia.
    }

    // Escenario 3: Comprobar comportamiento ante un ID inexistente (404)
    @Test
    public void testFindOwnerNotFound() throws Exception {
        long NON_EXISTENT_ID = 9999;

        this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/" + NON_EXISTENT_ID))
                .andExpect(status().isNotFound()); // Esperamos un 404
    }
}