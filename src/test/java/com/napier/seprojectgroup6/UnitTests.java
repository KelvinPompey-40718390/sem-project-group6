package com.napier.seprojectgroup6;

import com.napier.seprojectgroup6.navigation.*;
import com.napier.seprojectgroup6.reports.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
/*
    @author Anton Jardine
 */
public final class UnitTests
{

    public static ReportMenu reportMenu;

    private UnitTests() {}
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
