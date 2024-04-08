/**
 * Use Case 26
 */

package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.CapitalCity;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class WorldPopulation implements Report {

    private Connection con = null;
    public ArrayList<WorldPopulation> worldPopulation;


    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        this.executeQuery();
        this.displayWorldPopulation();
    }

    private void displayWorldPopulation() {
    }


    // Execute query when input is provided
    private void executeQuery()
    {
        worldPopulation = new ArrayList<>();

        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "";

            strSelect = "SELECT SUM(Population) AS WorldPopulation"+
                    "FROM country";
        }

        catch (Exception e)
    {
        System.out.println(e.getMessage());
        System.out.println("Failed to get population details");
    }
}

public void displayCapitalCities() {
    if(this.worldPopulation == null) {
        return;
    }

    System.out.println("Population of the World");
    System.out.printf("%^-30s","POPULATION");
    for(WorldPopulation worldPopulation: worldPopulation) {
        this.displayWorldPopulation();
    }
}


    }