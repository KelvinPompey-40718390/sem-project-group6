package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.City;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CitiesInContinent implements Report {

    private Connection con = null;
    public ArrayList<City> cities;
    private String continent;
    public Integer limit;


    public CitiesInContinent() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        continent = this.getContinent();

        this.executeQuery();
        this.displayCities();
    }

    public void runWithContinent(Integer limit, String continent ) {
        this.limit = limit;
        this.continent = continent;
        this.executeQuery();
        this.displayCities();
    }

    private void getInput() {
        this.limit = this.getN();
        this.continent = this.getContinent();
    }

    private Integer getN() {
        String input = Utils.readInput("Enter N for the number of cities to display");
        return Integer.parseInt(input);
    }

    private String getContinent() {
        String input = Utils.readInput("Enter the continent");
        return input;
    }

    // Execute query with inputs provided
    private void executeQuery()
    {
        cities = new ArrayList<>();

        if(this.continent.isEmpty()) {
            return;
        }
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

           // Create string for SQL statement
            String strSelect = "";

            // Limit results based on user Input
            if (this.limit > 0) {
                strSelect = "select * from city," +
                            " country where city.CountryCode = country.Code and country.Continent = '" + this.continent+ "' " +
                            "order by city.Population desc"+
                            "LIMIT " + this.limit;
            }
            // If a 0 is entered return all the results of the Query
            else {
                strSelect = "select * from city, " +
                            "country where city.CountryCode = country.Code and country.Continent = '" + this.continent+ "' " +
                            "order by city.Population desc";
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

        System.out.println(" All Cities In Continent: " + continent + "\n");
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
