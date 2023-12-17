package com.crosskey.worktest.service;

import com.crosskey.worktest.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CsvFileServiceTest {

    @Autowired
    private CsvFileService csvFileService;

    @TempDir
    Path tempDir;

    private File createTestCsvFile(String content) throws Exception {
        File csvFile = tempDir.resolve("test.csv").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            writer.write(content);
        }
        return csvFile;
    }

    @BeforeEach
    void setUp() {
        // nothing here for now
    }

    @Test
    void readCustomersFromCsv_ShouldParseValidData() throws Exception {
        String csvContent = "Customer,Total loan,Interest,Years\n"
                + "Alice,1000.0,5.0,2\n"
                + "Bob,2000.0,3.0,4";
        File csvFile = createTestCsvFile(csvContent);

        List<Customer> customers = csvFileService.readCustomersFromCsv(csvFile.getAbsolutePath());

        assertNotNull(customers);
        assertEquals(2, customers.size());

        Customer alice = customers.get(0);
        assertEquals("Alice", alice.getName());
        assertEquals(1000.0, alice.getTotalLoan());
        assertEquals(5.0, alice.getInterestRate());
        assertEquals(2, alice.getYears());

        Customer bob = customers.get(1);
        assertEquals("Bob", bob.getName());
        assertEquals(2000.0, bob.getTotalLoan());
        assertEquals(3.0, bob.getInterestRate());
        assertEquals(4, bob.getYears());
    }

    @Test
    void readCustomersFromCsv_ShouldHandleEmptyFile() throws Exception {
        File emptyCsvFile = createTestCsvFile("");

        List<Customer> customers = csvFileService.readCustomersFromCsv(emptyCsvFile.getAbsolutePath());

        assertTrue(customers.isEmpty(), "List should be empty for an empty CSV file");
    }

    @Test
    void readCustomersFromCsv_ShouldHandleMissingFields() throws Exception {
        String csvContent = "Customer,Total loan,Interest,Years\n"
                + "Alice,,5.0,2\n"
                + "Bob,2000.0,3.0,";
        File csvFile = createTestCsvFile(csvContent);

        List<Customer> customers = csvFileService.readCustomersFromCsv(csvFile.getAbsolutePath());

        assertNotNull(customers);
        assertEquals(2, customers.size());

        Customer alice = customers.get(0);
        assertEquals("Alice", alice.getName());
        assertEquals(0.0, alice.getTotalLoan(), "Missing loan amount should default to 0.0");
        assertEquals(5.0, alice.getInterestRate());
        assertEquals(2, alice.getYears());

        Customer bob = customers.get(1);
        assertEquals("Bob", bob.getName());
        assertEquals(2000.0, bob.getTotalLoan());
        assertEquals(3.0, bob.getInterestRate());
        assertEquals(0, bob.getYears(), "Missing years should default to 0");
    }

    @Test
    void readCustomersFromCsv_ShouldSkipInvalidRecords() throws Exception {
        String csvContent = "Customer,Total loan,Interest,Years\n"
                            + "Alice,invalidLoan,5.0,2\n"
                            + "Bob,2000.0,3.0,4";
        File csvFile = createTestCsvFile(csvContent);
    
        List<Customer> customers = csvFileService.readCustomersFromCsv(csvFile.getAbsolutePath());
    
        assertNotNull(customers, "Customers list should not be null");
        assertEquals(1, customers.size(), "Should skip invalid records and parse valid ones");
    
        Customer bob = customers.get(0);
        assertEquals("Bob", bob.getName());
        assertEquals(2000.0, bob.getTotalLoan());
        assertEquals(3.0, bob.getInterestRate());
        assertEquals(4, bob.getYears());
    }    

}
