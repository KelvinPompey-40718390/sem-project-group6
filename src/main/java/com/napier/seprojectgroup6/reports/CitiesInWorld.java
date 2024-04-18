package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.City;
import com.napier.seprojectgroup6.db.Country;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CitiesInWorld implements Report {

    private Connection con = null;
    public ArrayList<City> cities;
    public ArrayList<Country> countries;
    private Integer limit;
    public String city;
    public CitiesInWorld() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        city = this.getCity();
        limit = Integer.parseInt(this.getInput());
        this.executeQuery();
        this.displayCities();
    }

    public void runWithInputs(Integer limit, String city) {
        this.city = city;
        this.limit = limit;
        this.executeQuery();
        this.displayCities();
    }

    private String getInput() {
        return Utils.readInput("Enter Number of Cities to display, or 0 to Show All");
    }

    private String getCity()
    {
        return Utils.readInput("Enter Name of City");
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
                strSelect = "SELECT city.Name,country.Name AS CountryName,  city.District, city.Population\n" +
                        "FROM city\n" +
                        "INNER JOIN country ON city.CountryCode = country.Code\n" +
                        "WHERE country.Continent\n" +
                        "ORDER BY city.Population Desc " +
                        "LIMIT " + this.limit;
            }

            else {
                strSelect =     "SELECT city.Name,country.Name AS CountryName,  city.District, city.Population\n" +
                        "FROM city\n" +
                        "INNER JOIN country ON city.CountryCode = country.Code\n" +
                        "WHERE country.Continent\n" +
                        "ORDER BY city.Population Desc ";
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

        System.out.println("Top N and All Cities In the World: ");
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
