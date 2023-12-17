package com.crosskey.worktest.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.crosskey.worktest.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class CsvFileService {

    public List<Customer> readCustomersFromCsv(String filePath) {
        List<Customer> customers = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreSurroundingSpaces().withTrim())) {

            for (CSVRecord record : csvParser) {
                try {
                    Customer customer = new Customer();
                    customer.setName(record.get("Customer"));
                    customer.setTotalLoan(parseDouble(record.get("Total loan")));
                    customer.setInterestRate(parseDouble(record.get("Interest")));
                    customer.setYears(parseInt(record.get("Years")));
                    customers.add(customer);
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid record: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    private double parseDouble(String value) throws NumberFormatException {
        return value != null && !value.trim().isEmpty() ? Double.parseDouble(value.trim()) : 0.0;
    }

    private int parseInt(String value) throws NumberFormatException {
        return value != null && !value.trim().isEmpty() ? Integer.parseInt(value.trim()) : 0;
    }
}
