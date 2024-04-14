/**
 * Use Case 29
 */

package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TotalInCountry implements Report {

    private Connection con = null;
    public ArrayList<TotalInCountry> totalInCountry;
    public long TotalInCountry;
    private String country;
    private String inCityPercentage;
    private String outCityPercentage;

    public TotalInCountry() { this.con = ConnectionManager.getInstance().getConnection();}

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        country = this.getCountry();
        this.executeQuery();
        this.displayTotalInCountry();
    }

    //For Testing Application
    public void runWithInputs(String country) {
        this.country = country;
        this.executeQuery();
        this.displayTotalInCountry();
    }

    // Request country from user to generate report.
    private String getCountry()
    {
        return Utils.readInput("Enter Name of Country");
    }

    // Execute query when input is provided
    private void executeQuery() {
        totalInCountry = new ArrayList<>();

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "";

            strSelect ="SELECT SUM(country.Population) AS Population, " +
                    "IFNULL(CONCAT(ROUND((SUM(city.Population)/SUM(country.Population)) * 100,2), '%'),'0.00%') AS InCityPct, " +
                    "IFNULL(CONCAT(ROUND(((SUM(country.Population) - SUM(city.Population))/SUM(country.Population)) * 100,2),'%'),'0.00%') AS OutCityPct " +
                    "FROM world.country " +
                    "LEFT JOIN city ON country.Capital = city.ID " +
                    "WHERE country.Name = '" + country + "'";



            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                TotalInCountry = rset.getLong("Population");
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

    public void displayTotalInCountry() {
        if(this.totalInCountry == null) {
            return;
        }
        System.out.println("POPULATION OF THE COUNTRY:");
        System.out.println("--------------------------------------");
        System.out.printf("%-20s | %-20s | %-20s%n", "Total Population", "Population in Cities", "Population Outside Cities");
        System.out.printf("%-20d | %-20s | %-20s%n", TotalInCountry, inCityPercentage, outCityPercentage);
        System.out.println("--------------------------------------");
    }


}

