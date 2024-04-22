/**
 * Use Case 30
 */

package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TotalInDistrict implements Report {

    private final Connection con;
    public long total;
    private String district;
    private String inCityPercentage;
    private String outCityPercentage;

    public TotalInDistrict() { this.con = ConnectionManager.getInstance().getConnection();}

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        district = this.getDistrict();
        this.executeQuery();
        this.displayTotalInDistrict();
    }

    //For Testing Application
    public void runWithInputs(String district) {
        this.district = district;
        this.executeQuery();
        this.displayTotalInDistrict();
    }

    // Request district from user to generate report.
    private String getDistrict()
    {
        return Utils.readInput("Enter Name of District");
    }

    // Execute query when input is provided
    private void executeQuery() {

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement

            String strSelect ="SELECT SUM(city.Population) AS Population, " +
                    "IFNULL(CONCAT(ROUND((SUM(city.Population)/SUM(country.Population)) * 100,2), '%'),'0.00%') AS InCityPct, " +
                    "IFNULL(CONCAT(ROUND(((SUM(country.Population) - SUM(city.Population))/SUM(country.Population)) * 100,2),'%'),'0.00%') AS OutCityPct " +
                    "FROM world.city " +
                    "INNER JOIN country ON country.Code = city.CountryCode " +
                    "WHERE city.District = '" + district + "'";

            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                total = rset.getLong("Population");
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

    public void displayTotalInDistrict() {


        System.out.println("POPULATION OF " +this.district.toUpperCase());
        System.out.println("--------------------------------------");
        System.out.printf("%-20s | %-20s | %-20s%n", "Total Population", "Population in Cities", "Population Outside Cities");
        System.out.printf("%-20d | %-20s | %-20s%n", this.total, inCityPercentage, outCityPercentage);
        System.out.println("--------------------------------------");
    }


}

