/**
 * Use Case 27
 */

package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TotalInContinent implements Report {

    private Connection con = null;
    public ArrayList<TotalInContinent> totalInContinent;
    public long TotalInContinent;
    private String continent;
    private String inCityPercentage;
    private String outCityPercentage;

    public TotalInContinent() { this.con = ConnectionManager.getInstance().getConnection();}

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        continent = this.getContinent();
        this.executeQuery();
        this.displayTotalInContinent();
    }

    //For Testing Application
    public void runWithInputs(String continent) {
        this.continent = continent;
        this.executeQuery();
        this.displayTotalInContinent();
    }

    // Request continent from user to generate report.
    private String getContinent()
    {
        return Utils.readInput("Enter Name of Continent");
    }

    // Execute query when input is provided
    private void executeQuery() {
        totalInContinent = new ArrayList<>();

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "";

            strSelect = "SELECT SUM(country.Population) AS Population, " +
                    "IFNULL(CONCAT(ROUND((SUM(city.Population)/SUM(country.Population)) * 100,2), '%'),'0.00%') AS InCityPct, " +
                    "IFNULL(CONCAT(ROUND(((SUM(country.Population) - SUM(city.Population))/SUM(country.Population)) * 100,2),'%'),'0.00%') AS OutCityPct " +
                    "FROM world.country " +
                    "LEFT JOIN city ON country.Capital = city.ID " +
                    "WHERE country.Continent = '" + continent + "'";

                    //Execute SQL statement
                    ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                TotalInContinent = rset.getLong("Population");
                inCityPercentage = rset.getString("InCityPct");
                outCityPercentage = rset.getString("OutCityPct");
                //System.out.println("Population in cities %: " + inCityPercentage);
                //System.out.println("Population outside cities %: " + outCityPercentage);

            }
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    public void displayTotalInContinent() {
        if(this.totalInContinent == null) {
            return;
        }

        System.out.println("POPULATION OF THE CONTINENT:" + this.continent.toUpperCase());
        System.out.println("--------------------------------------");
        System.out.printf("%-20s | %-20s | %-20s%n", "Total Population", "Population in Cities", "Population Outside Cities");
        System.out.printf("%-20d | %-20s | %-20s%n", TotalInContinent, inCityPercentage, outCityPercentage);
        System.out.println("--------------------------------------");
    }


}