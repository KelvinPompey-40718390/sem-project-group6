package com.napier.seprojectgroup6;

import com.napier.seprojectgroup6.db.*;
import com.napier.seprojectgroup6.navigation.*;
import com.napier.seprojectgroup6.reports.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests
{
    static ReportMenu reportMenu;
    static PopulatedCapitalCitiesByContinent populatedCapitalCitiesByContinent;

    // Initialize Classes for Testing
    @BeforeAll
    static void init()
    {
        reportMenu = new ReportMenu("Population of City Report", PopulationOfACity.class.getName());

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

}
