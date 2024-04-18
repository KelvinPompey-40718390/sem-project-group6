package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.City;
import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.db.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TopPopulatedCitiesByContinentReport implements Report {

    private Connection con = null;
    public ArrayList<City> cities;
    public ArrayList<Country> countries;
    private Integer limit;
    public String continent;


    public TopPopulatedCitiesByContinentReport() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        this.getInput();
        this.executeQuery();
        this.displayCities();
    }

    public void runWithLimitAndContinent(Integer limit, String continent ) {
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

    private void executeQuery()
    {
        cities = new ArrayList<>();

        if(this.limit == null) {
            return;
        }
        try
        {
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
                city.ID = rset.getInt("ID");
              //  city.countryName = rset.getString("CountryName");
                city.population = rset.getInt("Population");
                city.countryCode = rset.getString("CountryCode");
                city.district = rset.getString("District");

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

        System.out.println(" Cities In A Continent ");
        System.out.printf("%-35s %-40s %-30s %-15s\n",  "CITY", "COUNTRY", "DISTRICT", "POPULATION");
        for(City city: cities) {
            this.displayCity(city);
        }
    }

    private void displayCity(City city) {
        if(city != null) {
            System.out.printf("%-35s %-10s %-30s %-15s\n",  city.name, city.countryCode, city.district, city.population);
        }
    }


}
