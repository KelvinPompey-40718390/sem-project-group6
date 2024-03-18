package com.napier.seprojectgroup6;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        //app.connect("localhost:33060", 30000);

    }

    @Test
    void testDemo() {
        assertEquals(4, 4);
    }
}