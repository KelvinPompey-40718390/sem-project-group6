package com.napier.seprojectgroup6;

import com.napier.seprojectgroup6.db.City;
import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.reports.CitiesByDistrictReport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CitiesByDistrictReportIntegrationTest
{
    static CitiesByDistrictReport report;

    @BeforeAll
    static void init()
    {
        ConnectionManager.getInstance().connect("localhost:33060", 30000);
        report = new CitiesByDistrictReport();
    }

    @Test
    void testRunWithEmptyDistrct() {
        City city = new City();

        report.runWithDistrict("");
        assertEquals(report.cities.size(), 0);
    }

    @Test
    void testRunWithDistrct() {
        City city = new City();

        report.runWithDistrict("Aichi");
        assertEquals(report.cities.size(), 15);
    }

}