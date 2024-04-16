/**
 * UC 16
 */

package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.CapitalCity;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PopulatedCapitalCitiesByDistrict implements Report {

    private Connection con = null;
    public ArrayList<CapitalCity> capitalCities;
    private Integer limit;
    private String district;

    public PopulatedCapitalCitiesByDistrict() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        district = this.getDistrict();
        limit = Integer.parseInt(this.getInput());
        this.executeQuery();
        this.displayCapitalCities();
    }

    // For use when testing the Application
    public void runWithInputs(Integer limit, String district) {
        this.district = district;
        this.limit = limit;
        this.executeQuery();
        this.displayCapitalCities();
    }

    // Ask user for input to generate report.
    private String getInput() {
        return Utils.readInput("Enter number of Capital cities to display, or 0 to show all");
    }

    private String getDistrict()
    {
        return Utils.readInput("Enter Name of District");
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
                        "WHERE city.District = '" + this.district +"' " +
                        "ORDER BY city.Population Desc " +
                        "LIMIT " + this.limit;
            }
            // If a 0 is entered return all the results of the Query
            else {
                strSelect = "SELECT city.name AS CityName, country.name AS CountryName, city.Population " +
                        "FROM city " +
                        "INNER JOIN country ON country.Code = city.CountryCode " +
                        "WHERE city.District = '" + this.district +"' " +
                        "ORDER BY city.Population Desc ";
                ;

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

        System.out.println("Populated Capital Cities by District: "+ this.district);
        System.out.printf("%-30s %-40s %-10s\n",  "NAME", "COUNTRY", "POPULATION");
        for(CapitalCity capitalCity: capitalCities) {
            this.displayCapitalCity(capitalCity);
        }
    }

    private void displayCapitalCity(CapitalCity capitalCity) {
        if(capitalCity != null) {
            System.out.printf("%-30s %-40s %-10s \n",  capitalCity.name, capitalCity.country, capitalCity.population);
        }
    }

}
