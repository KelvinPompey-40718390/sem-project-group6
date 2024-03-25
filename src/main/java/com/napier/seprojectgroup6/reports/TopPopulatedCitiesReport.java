package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.City;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TopPopulatedCitiesReport implements Report {

    private Connection con = null;
    public ArrayList<City> cities;
    private Integer limit;


    public TopPopulatedCitiesReport() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        limit = Integer.parseInt(this.getInput());
        this.executeQuery();
        this.displayCities();
    }

    public void runWithLimit(Integer limit) {
        this.limit = limit;
        this.executeQuery();
        this.displayCities();
    }

    private String getInput() {
        return Utils.readInput("Enter N for the number of cities to display");
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
                strSelect = "select ID, Name, CountryCode, District, Population from city " +
                        "order by Population desc limit " + this.limit + ";";
            }
            else {
                strSelect = "";
            }


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                City city = new City();
                city.name = rset.getString("Name");
                city.ID = rset.getInt("ID");
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

        System.out.println("Top " + this.limit + " populated cities\n");
        System.out.printf("%-20s %-10s %-10s %-10s\n",  "City", "ID", "Population", "Country");
        for(City city: cities) {
            this.displayCity(city);
        }
    }

    private void displayCity(City city) {
        if(city != null) {
            System.out.printf("%-20s %-10s %-10s %-10s\n",  city.name, city.ID, city.population, city.countryCode);
        }
    }


}