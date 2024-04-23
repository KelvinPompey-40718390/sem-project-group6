/**
 * This Reports covers Use Case 19 and 22
 */

package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.CapitalCity;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PopulatedCapitalCitiesByRegion implements Report {

    private final Connection con;
    public ArrayList<CapitalCity> capitalCities;
    private Integer limit;
    private String region;

    // Establish connection to Database
    public PopulatedCapitalCitiesByRegion() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public boolean run() {
        region = this.getRegion();
        limit = Integer.parseInt(this.getInput());
        this.executeQuery();
        this.displayCapitalCities();
        return true;
    }

    // For use when testing the Application
    public void runWithInputs(Integer limit, String region) {
        this.region = region;
        this.limit = limit;
        this.executeQuery();
        this.displayCapitalCities();
    }

    // Ask user for input to generate report.
    private String getInput() {
        return Utils.readInput("Enter number of Capital cities to display, or 0 to show all");
    }

    // Ask User to Enter the Region
    private String getRegion()
    {
        return Utils.readInput("Enter Name of Region");
    }

    // Execute query with inputs provided
    private void executeQuery()
    {
        capitalCities = new ArrayList<>();

        if(this.limit == null) {
            return;
        }
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            // If a 0 is entered return all the results of the Query


            String strSelect = "SELECT city.name AS CityName, country.name AS CountryName, city.Population " +
                    "FROM city " +
                    "INNER JOIN country ON country.Code = city.CountryCode " +
                    "WHERE country.Region = '" + this.region +"' " +
                    "ORDER BY city.Population Desc ";

            // Apply Limit to Query Results
            if(this.limit > 0) {
                strSelect = "SELECT city.name AS CityName, country.name AS CountryName, city.Population " +
                            "FROM city " +
                            "INNER JOIN country ON country.Code = city.CountryCode " +
                            "WHERE country.Region = '" + this.region +"' " +
                            "ORDER BY city.Population Desc " +
                            "LIMIT " + this.limit;
            }

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                CapitalCity capitalCity = new CapitalCity();
                capitalCity.name = rset.getString("CityName");
                capitalCity.country = rset.getString("CountryName");
                capitalCity.population = rset.getInt("Population");

                this.capitalCities.add(capitalCity);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    // Insert Header Rows in Preparation to Display Query results
    public void displayCapitalCities() {
        if(this.capitalCities == null) {
            return;
        }

        System.out.println("Populated Capital Cities by Region: "+ this.region);
        System.out.printf("%-30s %-40s %-10s\n",  "NAME", "COUNTRY", "POPULATION");
        for(CapitalCity capitalCity: capitalCities) {
            this.displayCapitalCity(capitalCity);
        }
    }

    // Display results of Query
    private void displayCapitalCity(CapitalCity capitalCity) {
        if(capitalCity != null) {
            System.out.printf("%-30s %-40s %-10s \n",  capitalCity.name, capitalCity.country, capitalCity.population);
        }
    }

}
