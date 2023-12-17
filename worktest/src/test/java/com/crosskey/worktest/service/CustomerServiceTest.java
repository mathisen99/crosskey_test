package com.crosskey.worktest.service;

import com.crosskey.worktest.model.Customer;
import com.crosskey.worktest.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("Alice");
        customer.setTotalLoan(1000.0);
        customer.setInterestRate(5.0);
        customer.setYears(2);
    }

    @Test
    void saveCustomer_ShouldSaveValidCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.saveCustomer(customer);

        assertNotNull(savedCustomer);
        assertEquals(customer.getName(), savedCustomer.getName());
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void getCustomerById_ShouldReturnCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Optional<Customer> foundCustomer = customerService.getCustomerById(1L);

        assertTrue(foundCustomer.isPresent());
        assertEquals(customer.getName(), foundCustomer.get().getName());
        verify(customerRepository).findById(1L);
    }

    @Test
    void updateCustomer_ShouldUpdateExistingCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(1L);
        updatedCustomer.setName("Alice Updated");
        updatedCustomer.setTotalLoan(2000.0);
        updatedCustomer.setInterestRate(6.0);
        updatedCustomer.setYears(3);

        Customer result = customerService.updateCustomer(1L, updatedCustomer);

        assertNotNull(result);
        assertEquals(updatedCustomer.getName(), result.getName());
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void deleteCustomer_ShouldDeleteCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).delete(any(Customer.class));

        customerService.deleteCustomer(1L);

        verify(customerRepository).delete(any(Customer.class));
    }
}
