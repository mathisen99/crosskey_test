package com.crosskey.worktest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCustomerValidation() {
        Customer customer = new Customer();
        customer.setName(null); // Invalid case, should trigger @NotNull
        customer.setTotalLoan(-1000); // Invalid case, should trigger @Positive
        customer.setInterestRate(-5); // Invalid case, should trigger @Positive
        customer.setYears(0); // Invalid case, should trigger @Min

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertFalse(violations.isEmpty(), "There should be constraint violations");
    }

    @Test
    void testValidCustomer() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setTotalLoan(1000);
        customer.setInterestRate(5);
        customer.setYears(2);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertTrue(violations.isEmpty(), "There should be no constraint violations for valid data");
    }

    @Test
    void testIdGetterSetter() {
        Customer customer = new Customer();
        customer.setId(1L);
        assertEquals(1L, customer.getId(), "Getter or setter for id is not working correctly");
    }

    @Test
    void testNameGetterSetter() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        assertEquals("John Doe", customer.getName(), "Getter or setter for name is not working correctly");
    }

    @Test
    void testTotalLoanGetterSetter() {
        Customer customer = new Customer();
        customer.setTotalLoan(1000.0);
        assertEquals(1000.0, customer.getTotalLoan(), "Getter or setter for total loan is not working correctly");
    }

    @Test
    void testInterestRateGetterSetter() {
        Customer customer = new Customer();
        customer.setInterestRate(5.0);
        assertEquals(5.0, customer.getInterestRate(), "Getter or setter for interest rate is not working correctly");
    }

    @Test
    void testYearsGetterSetter() {
        Customer customer = new Customer();
        customer.setYears(10);
        assertEquals(10, customer.getYears(), "Getter or setter for years is not working correctly");
    }

    @Test
    void testMonthlyPaymentGetterSetter() {
        Customer customer = new Customer();
        customer.setMonthlyPayment(500.0);
        assertEquals(500.0, customer.getMonthlyPayment(), "Getter or setter for monthly payment is not working correctly");
    }
}
