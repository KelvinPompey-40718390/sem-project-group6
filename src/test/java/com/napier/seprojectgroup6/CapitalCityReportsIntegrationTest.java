package com.napier.seprojectgroup6;

import com.napier.seprojectgroup6.db.CapitalCity;
import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.reports.PopulatedCapitalCitiesByContinent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CapitalCityReportsIntegrationTest {

    static PopulatedCapitalCitiesByContinent report;

    @BeforeAll
    static void init()
    {
        ConnectionManager.getInstance().connect("localhost:33060", 30000);
        report = new PopulatedCapitalCitiesByContinent();
    }

    @Test
    void testRunWithEmptyContinent()
    {
        CapitalCity capitalCity = new CapitalCity();

        report.runWithInputs(0,"");
        assertEquals(report.capitalCities.size(),0);
    }

    @Test
    void testRunWithEmptyLimitAndContinent()
    {
        CapitalCity capitalCity = new CapitalCity();

        report.runWithInputs(null,"");
        assertEquals(report.capitalCities.size(),0);
    }

    @Test
    void testRunWithEmptyLimitWithContinent()
    {
        CapitalCity capitalCity = new CapitalCity();

        report.runWithInputs(null,"Africa");
        assertEquals(report.capitalCities.size(),0);
    }

    @Test
    void testRunWithContinent()
    {
        CapitalCity capitalCity = new CapitalCity();

        report.runWithInputs(0,"Africa");
        assertEquals(report.capitalCities.size(),366);
    }

    @Test
    void testRunWithIncorrectContinent()
    {
        CapitalCity capitalCity = new CapitalCity();

        report.runWithInputs(0,"Test");
        assertEquals(report.capitalCities.size(),0);
    }

    @Test
    void testRunWithLimitAndContinent()
    {
        CapitalCity capitalCity = new CapitalCity();

        report.runWithInputs(5,"Africa");
        assertEquals(report.capitalCities.size(),5);
    }

}
