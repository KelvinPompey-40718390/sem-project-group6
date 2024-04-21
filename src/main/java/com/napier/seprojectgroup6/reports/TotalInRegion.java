/**
 * Use Case 28
 */

package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TotalInRegion implements Report {

    private Connection con = null;
    public ArrayList<TotalInRegion> totalInRegion;
    public long TotalInRegion;
    private String region;
    private String inCityPercentage;
    private String outCityPercentage;

    public TotalInRegion() { this.con = ConnectionManager.getInstance().getConnection();}

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        region = this.getRegion();
        this.executeQuery();
        this.displayTotalInRegion();
    }

    //For Testing Application
    public void runWithInputs(String region) {
        this.region = region;
        this.executeQuery();
        this.displayTotalInRegion();
    }

    // Request region from user to generate report.
    private String getRegion()
    {
        return Utils.readInput("Enter Name of Region");
    }

    // Execute query when input is provided
    private void executeQuery() {
        totalInRegion = new ArrayList<>();

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
                    "WHERE Region = '" + region + "'";


            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                TotalInRegion = rset.getLong("Population");
                inCityPercentage = rset.getString("InCityPct");
                outCityPercentage = rset.getString("OutCityPct");
            }
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    public void displayTotalInRegion() {
        if(this.totalInRegion == null) {
            return;
        }

        System.out.println("POPULATION OF " + this.region.toUpperCase());
        System.out.println("--------------------------------------");
        System.out.printf("%-20s | %-20s | %-20s%n", "Total Population", "Population in Cities", "Population Outside Cities");
        System.out.printf("%-20d | %-20s | %-20s%n", TotalInRegion, inCityPercentage, outCityPercentage);
        System.out.println("--------------------------------------");
    }


}

