package ua.lviv.iot.WateringSystem.dal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class LocationFilestoreTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveRecords() throws IOException {
        File generatedData = new File(("src/test/resources/location-test.csv"));
        File expectedData = new File("src/main/resources/files/location-2023-05-31.csv");
        try (
                BufferedReader expectedReader = new BufferedReader(new FileReader(generatedData));
                BufferedReader actualReader = new BufferedReader(new FileReader(expectedData))) {

            List<String> expected = new ArrayList<>();
            List<String> result = new ArrayList<>();
            String expectedLine = null;
            String actualLine = null;
            while ((expectedLine = expectedReader.readLine()) != null) {
                expected.add(expectedLine);

            }
            while ((actualLine = actualReader.readLine()) != null) {
                result.add(actualLine);

            }
            Assertions.assertLinesMatch(expected, result);
        }
    }
}
