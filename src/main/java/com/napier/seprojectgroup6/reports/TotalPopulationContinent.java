/**
 * Use Case 27
 */

package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.CapitalCity;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

    public class TotalPopulationContinent implements Report {
    private Connection con = null;
    public ArrayList<TotalPopulationContinent> totalPopulationContinent;
    private Long TotalPopulationContinent;

    public TotalPopulationContinent() { this.con = ConnectionManager.getInstance().getConnection();}

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        this.executeQuery();
    }

    public void displayTotalPopulationContinent() {
    }


    // Execute query when input is provided
    private void executeQuery() {
        totalPopulationContinent = new ArrayList<>();

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "";

            strSelect = "SELECT SUM(Population) AS ContinentPopulation"+
            "FROM country"+
            "WHERE Continent = 'Europe'";

            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                TotalPopulationContinent totalPopulationContinent= new TotalPopulationContinent();
                totalPopulationContinent.TotalPopulationContinent = rset.getLong("Population");

            }
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    public void displayCapitalCities() {
        if(this.totalPopulationContinent == null) {
            return;
        }

        System.out.println("Continent Population");
        System.out.printf("%-30s","POPULATION");

    }
}

