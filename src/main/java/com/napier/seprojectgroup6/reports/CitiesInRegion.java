package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.City;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CitiesInRegion implements Report {

    private final Connection con;
    public ArrayList<City> cities;
    private String region;
    public String city;
    private Integer limit;

    public CitiesInRegion() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and execute the query
     */
    public void run() {
        region = this.getRegion();
        limit = Integer.parseInt(this.getInput());
        this.executeQuery();
        this.displayCities();
    }

    public void runWithRegionAndLimits(Integer limit, String region) {
        this.limit = limit;
        this.region = region;
        this.executeQuery();
        this.displayCities();
    }
    private String getInput() { return Utils.readInput("Enter Number of Cities to display, or 0 to Show All");   }
    private String getRegion() {
        return Utils.readInput("Enter Region");
    }

    private void executeQuery()
    {
        cities = new ArrayList<>();

        if(this.region.isEmpty()) {
            return;
        }
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            // If a 0 is entered return all the results of the Query

            String strSelect = "SELECT city.Name,country.Name AS CountryName,  city.District, city.Population, country.Region\n" +
                    "FROM city\n" +
                    "INNER JOIN country ON city.CountryCode = country.Code\n" +
                    "WHERE country.Region = '" + this.region + "'" +
                    "ORDER BY city.Population DESC ";

            // Limit results based on user Input
            if(this.limit > 0) {

                strSelect = "SELECT city.Name,country.Name AS CountryName,  city.District, city.Population, country.Region\n" +
                        "FROM city\n" +
                        "INNER JOIN country ON city.CountryCode = country.Code\n" +
                        "WHERE country.Region = '" + this.region + "'" +
                        "ORDER BY city.Population DESC " +
                        "LIMIT " + this.limit;
            }


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                City city = new City();
                city.name = rset.getString("Name");
                city.countryName = rset.getString("CountryName");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");

                this.cities.add(city);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    public void displayCities() {
        if(this.cities == null) {
            return;
        }

        System.out.println("All Cities by Region: " + region + "\n");
        System.out.printf("%-35s %-40s %-30s %-15s\n",  "CITY", "COUNTRY", "DISTRICT", "POPULATION");
        for(City city: cities) {
            this.displayCity(city);
        }
    }

    private void displayCity(City city) {
        if(city != null) {
            System.out.printf("%-35s %-40s %-30s %-15s\n",  city.name, city.countryName, city.district, city.population);
        }
    }


}
