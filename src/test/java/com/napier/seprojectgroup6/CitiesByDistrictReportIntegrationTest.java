package com.napier.seprojectgroup6;

import com.napier.seprojectgroup6.db.City;
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
        report = new CitiesByDistrictReport();
    }

    @Test
    void testRunWithDistrct() {
        City city = new City();

        report.runWithDistrict("");
    }

    @Test
    void demoTest2()
    {
        assertEquals(5, 5);
    }

}