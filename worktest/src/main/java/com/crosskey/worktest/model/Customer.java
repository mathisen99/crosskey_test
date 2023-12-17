package com.crosskey.worktest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Customer name cannot be null")
    private String name;

    @Positive(message = "Total loan must be positive")
    private double totalLoan;

    @Positive(message = "Interest rate must be positive")
    private double interestRate;

    @Min(value = 1, message = "Years must be at least 1")
    private int years;

    private double monthlyPayment;

    public Customer() {
    }

    public Customer(Long id, String name, double totalLoan, double interestRate, int years, double monthlyPayment) {
        this.id = id;
        this.name = name;
        this.totalLoan = totalLoan;
        this.interestRate = interestRate;
        this.years = years;
        this.monthlyPayment = monthlyPayment;
    }
    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(double totalLoan) {
        this.totalLoan = totalLoan;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}
