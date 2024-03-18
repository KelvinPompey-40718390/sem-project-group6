package com.napier.seprojectgroup6;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void demoTest()
    {
        assertEquals(5, 5);
    }

    @Test
    void demoTest2()
    {
        assertEquals(5, 5);
    }

}