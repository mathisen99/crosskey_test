package com.crosskey.worktest.service;

import com.crosskey.worktest.model.Customer;
import com.crosskey.worktest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        validateCustomerData(customer);
        calculateMortgage(customer);
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) throws IllegalArgumentException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found for this id :: " + id));
        validateCustomerData(customerDetails);
        calculateMortgage(customerDetails);
        customer.setName(customerDetails.getName());
        customer.setTotalLoan(customerDetails.getTotalLoan());
        customer.setInterestRate(customerDetails.getInterestRate());
        customer.setYears(customerDetails.getYears());
        customer.setMonthlyPayment(customerDetails.getMonthlyPayment());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found for this id :: " + id));
        customerRepository.delete(customer);
    }

    private void calculateMortgage(Customer customer) {
        double monthlyRate = customer.getInterestRate() / 100 / 12;
        int totalPayments = customer.getYears() * 12;
        customer.setMonthlyPayment(calculateMonthlyPayment(customer.getTotalLoan(), monthlyRate, totalPayments));
    }

    private double calculateMonthlyPayment(double totalLoan, double monthlyRate, int totalPayments) {
        double numerator = totalLoan * monthlyRate * Math.pow(1 + monthlyRate, totalPayments);
        double denominator = Math.pow(1 + monthlyRate, totalPayments) - 1;
        return numerator / denominator;
    }

    private void validateCustomerData(Customer customer) {
        if (customer.getTotalLoan() <= 0) {
            throw new IllegalArgumentException("Total loan amount must be positive.");
        }
        if (customer.getInterestRate() <= 0) {
            throw new IllegalArgumentException("Interest rate must be positive.");
        }
        if (customer.getYears() <= 0) {
            throw new IllegalArgumentException("Years must be positive.");
        }
    }
}
