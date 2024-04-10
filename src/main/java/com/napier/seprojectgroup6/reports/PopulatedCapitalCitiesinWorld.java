package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.ConnectionManager;

// Import the Capital city Class for necessary Variables
import com.napier.seprojectgroup6.db.CapitalCity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PopulatedCapitalCitiesinWorld implements Report {

    private Connection con = null;
    public ArrayList<CapitalCity> capitalCities;
    private Integer limit;


    public PopulatedCapitalCitiesinWorld() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        limit = Integer.parseInt(this.getInput());
        this.executeQuery();
        this.displayCapitalCities();
    }

    // For use when testing the Application
    public void runWithInputs(Integer limit) {
        this.limit = limit;
        this.executeQuery();
        this.displayCapitalCities();
    }

    // Ask user for input to generate report.
    private String getInput() {
        return Utils.readInput("Enter number of Capital cities to display, or 0 to show all");
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
            String strSelect = "";

            if(this.limit > 0) {
                strSelect = "SELECT city.name AS CityName, country.name AS CountryName, city.Population " +
                        "FROM city " +
                        "INNER JOIN country ON country.Code = city.CountryCode " +
                        "ORDER BY city.Population Desc " +
                        "LIMIT " + this.limit;
            }
            // If a 0 is entered return all the results of the Query
            else {
                strSelect = "SELECT city.name AS CityName, country.name AS CountryName, city.Population " +
                        "FROM city " +
                        "INNER JOIN country ON country.Code = city.CountryCode " +
                        "ORDER BY city.Population Desc ";

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

    public void displayCapitalCities() {
        if(this.capitalCities == null) {
            return;
        }

        System.out.println("Populated Capital Cities in the World");
        System.out.printf("%-20s %-40s %-10s\n",  "NAME", "COUNTRY", "POPULATION");
        for(CapitalCity capitalCity: capitalCities) {
            this.displayCapitalCity(capitalCity);
        }
    }

    private void displayCapitalCity(CapitalCity capitalCity) {
        if(capitalCity != null) {
            System.out.printf("%-20s %-40s %-10s \n",  capitalCity.name, capitalCity.country, capitalCity.population);
        }
    }

}
