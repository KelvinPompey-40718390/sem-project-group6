package com.napier.seprojectgroup6;

import com.napier.seprojectgroup6.db.Population;
import com.napier.seprojectgroup6.navigation.*;
import com.napier.seprojectgroup6.navigation.ReportMenu;
import com.napier.seprojectgroup6.reports.CitiesByDistrictReport;
import com.napier.seprojectgroup6.reports.PopulationOfACity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests
{
    static ReportMenu reportMenu;
    static CitiesByDistrictReport citiesByDistrictReport;

    @BeforeAll
    static void init()
    {
        reportMenu = new ReportMenu("Population of City Report", PopulationOfACity.class.getName());
        citiesByDistrictReport = new CitiesByDistrictReport();
    }

    @Test
    void reportMenuTestTitle()
    {
        assertEquals(reportMenu.title, "Population of City Report");
    }

    @Test
    void reportMenuTestClass()
    {
        assertEquals(reportMenu.className, "PopulationOfACity");
    }
}
