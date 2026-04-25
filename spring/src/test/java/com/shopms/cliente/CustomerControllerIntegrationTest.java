package com.shopms.cliente;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopms.cliente.application.dto.AddressRequest;
import com.shopms.cliente.application.dto.CustomerRequest;
import com.shopms.cliente.infrastructure.persistence.repository.SpringDataCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SpringDataCustomerRepository repository;

    @BeforeEach
    void cleanDatabase() {
        repository.deleteAll();
    }

    @Test
    void shouldPerformCrudOperationsForCustomer() throws Exception {
        CustomerRequest request = new CustomerRequest(
                "Maria",
                LocalDate.of(1990, 5, 20),
                new AddressRequest("Rua das Flores", "123", "Sao Paulo", "SP")
        );

        MvcResult createResult = mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Maria"))
                .andExpect(jsonPath("$.address.city").value("Sao Paulo"))
                .andReturn();

        String responseBody = createResult.getResponse().getContentAsString();
        String customerId = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {}).get("id").toString();

        mockMvc.perform(get("/api/customers/" + customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Maria"));

        CustomerRequest updateRequest = new CustomerRequest(
                "Maria Silva",
                LocalDate.of(1990, 5, 20),
                new AddressRequest("Rua das Flores", "123", "Sao Paulo", "SP")
        );

        mockMvc.perform(put("/api/customers/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Maria Silva"));

        mockMvc.perform(delete("/api/customers/" + customerId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/customers/" + customerId))
                .andExpect(status().isNotFound());
    }
}
