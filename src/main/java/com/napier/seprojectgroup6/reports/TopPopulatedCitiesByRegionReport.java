package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.City;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TopPopulatedCitiesByRegionReport implements Report {

    private Connection con = null;
    public ArrayList<City> cities;
    private Integer limit;
    public String region;


    public TopPopulatedCitiesByRegionReport() {
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

    public void runWithLimitAndRegion(Integer limit, String region ) {
        this.limit = limit;
        this.region = region;
        this.executeQuery();
        this.displayCities();
    }

    private void getInput() {
        this.limit = this.getN();
        this.region = this.getRegion();
    }

    private Integer getN() {
        String input = Utils.readInput("Enter N for the number of cities to display");
        return Integer.parseInt(input);
    }

    private String getRegion() {
        String input = Utils.readInput("Enter the region");
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

            if(this.limit > 0) {
                strSelect = String.format("select * from city left join country on country.Code = city.CountryCode where Region = '%s' order by city.Population DESC limit %d;", this.region, this.limit);
            }
            else {
                strSelect = "";
            }


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.ID = rset.getInt("ID");
                city.population = rset.getInt("city.Population");
                city.countryCode = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.countryName = rset.getString("country.Name");

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

        System.out.println("\nTop " + this.limit + " populated cities" + " in " + this.region);
        System.out.printf("%-30s %-20s %-10s %-10s\n",  "Name", "District",  "Population", "Country");
        for(City city: cities) {
            this.displayCity(city);
        }

        // Give us an extra new line
        System.out.println();
    }

    private void displayCity(City city) {
        if(city != null) {
            System.out.printf("%-30s %-20s %-10d %-10s\n",  city.name, city.district, city.population, city.countryName);
        }
    }


}
