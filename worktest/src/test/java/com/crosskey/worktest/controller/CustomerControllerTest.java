package com.crosskey.worktest.controller;

import com.crosskey.worktest.model.Customer;
import com.crosskey.worktest.service.CustomerService;
import com.crosskey.worktest.service.CsvFileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @Mock
    private CsvFileService csvFileService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        Customer customer = new Customer(1L, "Alice", 1000.0, 5.0, 2, 50.0);
        given(customerService.getAllCustomers()).willReturn(Arrays.asList(customer));

        mockMvc.perform(get("/api/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Customer customer = new Customer(1L, "Alice", 1000.0, 5.0, 2, 50.0);
        given(customerService.getCustomerById(1L)).willReturn(Optional.of(customer));

        mockMvc.perform(get("/api/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    public void testCreateCustomer() throws Exception {
        Customer savedCustomer = new Customer(2L, "Bob", 2000.0, 4.0, 3, 60.0); // Assuming this is the customer after save
        given(customerService.saveCustomer(any(Customer.class))).willReturn(savedCustomer);
    
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Bob\", \"totalLoan\": 2000.0, \"interestRate\": 4.0, \"years\": 3}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Bob"))
                .andExpect(jsonPath("$.id").value(2L));
    }    

    @Test
    public void testUpdateCustomer() throws Exception {
        Customer updatedCustomer = new Customer(1L, "Alice Updated", 1500.0, 3.5, 4, 0);
        given(customerService.updateCustomer(eq(1L), any(Customer.class))).willReturn(updatedCustomer);

        mockMvc.perform(put("/api/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Alice Updated\", \"totalLoan\": 1500.0, \"interestRate\": 3.5, \"years\": 4}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice Updated"))
                .andExpect(jsonPath("$.totalLoan").value(1500.0));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        doNothing().when(customerService).deleteCustomer(1L);

        mockMvc.perform(delete("/api/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUploadFile() throws Exception {
        Customer uploadedCustomer = new Customer(null, "Charlie", 3000.0, 6.0, 5, 0);
        given(csvFileService.readCustomersFromCsv(anyString())).willReturn(Arrays.asList(uploadedCustomer));
        given(customerService.saveCustomer(any(Customer.class))).willReturn(uploadedCustomer);

        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/plain", "Some CSV content".getBytes());

        mockMvc.perform(multipart("/api/customers/upload").file(file))
                .andExpect(status().isOk())
                .andExpect(content().string("File uploaded and processed successfully"));
    }

}
