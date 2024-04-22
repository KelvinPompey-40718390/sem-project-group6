package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.City;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CitiesInCountry implements Report {

    private Connection con;
    public ArrayList<City> cities;
    private String country;


    public CitiesInCountry() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        country = this.getInput();
        this.executeQuery();
        this.displayCities();
    }

    public void runWithCountry(String country) {
        this.country = country;
        this.executeQuery();
        this.displayCities();
    }

    private String getInput() {
        return Utils.readInput("Enter Country");
    }

    private void executeQuery()
    {
        cities = new ArrayList<>();

        if(this.country.isEmpty()) {
            return;
        }
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name,country.Name AS CountryName,  city.District, city.Population, Region\n" +
                            "FROM city\n" +
                            "         INNER JOIN country ON city.CountryCode = country.Code\n" +
                            "WHERE Code = 'USA'\n" +
                            "ORDER BY city.Population DESC";
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

        System.out.println("Cities by Country: " + country + "\n");
        System.out.printf("%-35s %-40s %-30s %-15s\n",  "City", "Country", "District", "Population");
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
