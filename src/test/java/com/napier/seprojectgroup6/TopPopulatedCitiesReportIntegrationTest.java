package com.napier.seprojectgroup6;

import com.napier.seprojectgroup6.db.City;
import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.reports.CitiesByDistrictReport;
import com.napier.seprojectgroup6.reports.TopPopulatedCitiesReport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TopPopulatedCitiesReportIntegrationTest {

    static TopPopulatedCitiesReport report;

    @BeforeAll
    static void init()
    {
        ConnectionManager.getInstance().connect("localhost:33060", 30000);
        report = new TopPopulatedCitiesReport();
    }

    @Test
    void testRunWithEmptyDistrct() {
        City city = new City();

        report.runWithLimit(0);
        assertEquals(report.cities.size(), 0);
    }

    @Test
    void testRunWithDistrct() {
        City city = new City();

        report.runWithLimit(5);
        assertEquals(report.cities.size(), 5);
    }

}
