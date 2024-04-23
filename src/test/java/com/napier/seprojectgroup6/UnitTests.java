package com.napier.seprojectgroup6;

import com.napier.seprojectgroup6.db.CapitalCity;
import com.napier.seprojectgroup6.db.City;
import com.napier.seprojectgroup6.db.Country;
import com.napier.seprojectgroup6.db.Population;
import com.napier.seprojectgroup6.navigation.*;
import com.napier.seprojectgroup6.reports.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
/*
    @author Anton Jardine
 */

@ExtendWith(MockitoExtension.class)
public final class UnitTests
{

    public static ReportMenu reportMenu;

    @Mock public static Connection connection;
    @Mock public static InputReader inputReader;

    public static PopulationOfACity populationOfACity;

    private UnitTests() {}
    // Initialize Classes for Testing
    @BeforeAll
    static void init()
    {
        reportMenu = new ReportMenu("Population of City Report", PopulationOfACity.class.getName());
        populationOfACity = new PopulationOfACity();

    }

    /**
     * Report Menu Tests
     */
    @Test
    void reportMenuTestTitle()
    {
        assertEquals(reportMenu.title, "Population of City Report");
    }

    @Test
    void reportMenuTestClass()
    {
        assertEquals(reportMenu.className, "com.napier.seprojectgroup6.reports.PopulationOfACity");
    }

    @Test
    void testPopulationOfACityInputReader() {
        populationOfACity.setInputReader(inputReader);
        assertNotNull(populationOfACity.getInputReader());
    }

    @Test
    void testPopulationOfACityDisplayNullCity() {
        populationOfACity.setInputReader(inputReader);
        boolean success = populationOfACity.displayCity(null);
        assertFalse(success);
    }

    @Test
    void testPopulationOfACityDisplayCity() {
        City city = new City();
        city.name = "Paris";
        city.population = 100;
        city.countryName = "France";
        city.continent = "Europe";
        boolean success = populationOfACity.displayCity(city);
        assertTrue(success);
    }

    @Test
    void testCityClass() {
        City city = new City();
        assertNotNull(city);
    }

    @Test
    void testCityClaass() {
        City city = new City();
        assertNotNull(city);
    }

    @Test
    void testCountryClass() {
        Country country = new Country();
        assertNotNull(country);
    }

    @Test
    void testPopulationClass() {
        Population population = new Population();
        assertNotNull(population);
    }

    @Test
    void testCapitalCityClass() {
        CapitalCity capitalCity = new CapitalCity();
        assertNotNull(capitalCity);
    }
}