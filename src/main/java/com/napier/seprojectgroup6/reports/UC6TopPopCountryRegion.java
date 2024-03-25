package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.City;
import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.db.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class UC6TopPopCountryRegion {

    private Connection con = null;
    public ArrayList<City> countries;
    private Integer limit;


    public UC6TopPopCountryRegion () {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        limit = Integer.parseInt(this.getInput());
        this.executeQuery();
        this.displayCountries();
    }


    public void runWithLimit(Integer limit) {
        this.limit = limit;
        this.executeQuery();
        this.displayCountries();
    }

    private String getInput() {
        return Utils.readInput("Enter N for the number of countries to display");
    }

    private void executeQuery()
    {
        countries = new ArrayList<>();

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
                strSelect = "select country.code, country.Name, country.Continent,country.Region, country.Population, country.Capital from country\n" +
                        "where Region = 'caribbean'\n" +
                        "order by Population desc\n"
                        + this.limit + ";";
            }
            else {
                strSelect = "";
            }


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("CountryCode");
                country.name = rset.getString("Name");
                country.continent = rset.getInt("Continet");
                country.region = rset.getInt("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getString("Capital);


                this.countries.add(country);
            }


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    public void displayCities() {
        if(this.countries == null) {
            return;
        }

        System.out.println("Top " + this.limit + " populated countries in\n" + region + ":");
        System.out.printf("%-10s %-20s %-20s %-20s %-20s %-20s\n",  "Code", "Name", "Continent", "Region" , "Population" , "Capitol");
        for(Country Country: countries) {
            this.displayCountries(country);
        }
    }

    private void displayCountries(Country Country) {
        if(countries != null) {
            System.out.printf("%-10s %-20s %-20s %-20s %-20s %-20s\n", country.countryCode, country.name, country.continent, country.region, country.population, country.capital);
        }
    }


}