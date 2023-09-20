package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DirtiesContext
    void getCarModelsTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/audi"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Wrong car model. Only 'porsche' is allowed"));
    }

    @Test
    @DirtiesContext
    void getAllCarsTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("""
                        {
                            "errorMessage": "No items available"
                        }
                        """));
    }
}