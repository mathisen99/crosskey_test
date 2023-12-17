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
                Customer customer = new Customer();
                customer.setName(record.get("Customer"));
                customer.setTotalLoan(Double.parseDouble(record.get("Total loan")));
                customer.setInterestRate(Double.parseDouble(record.get("Interest")));
                customer.setYears(Integer.parseInt(record.get("Years")));
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }
}