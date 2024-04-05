package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.City;
import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.db.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TopPopulationCountriesRegion implements Report {

    private Connection con = null;
    public ArrayList<Country> countries;
    private Integer limit;

    private String region;


    public TopPopulationCountriesRegion() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        limit = Integer.parseInt(this.getInput());
        region = this.getRegion();
        this.executeQuery();
        this.displayCountries();
    }


    public void runWithLimit(Integer limit,String region) {
        this.limit = limit;
        this.region =region;
        this.executeQuery();
        this.displayCountries();
    }

    private String getInput() {
        return Utils.readInput("Enter N for the number of countries to display");
    }

    private String getRegion()
    {
        return Utils.readInput("Enter Name of Region");
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
                        "WHERE country.Region = '" + this.region +"' " +
                        "order by Population desc " +
                        "LIMIT " + this.limit;

            }
            else {
                strSelect = "";
            }


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                Country Country = new Country();
                Country.code = rset.getString("Code");
                Country.name = rset.getString("Name");
                Country.continent = rset.getString("Continent");
                Country.region = rset.getString("Region");
                Country.population = rset.getInt("Population");
                Country.capital = rset.getString("Capital");

                this.countries.add(Country);
            }


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    public void displayCountries() {
        if(this.countries == null) {
            return;
        }

        System.out.println("Top " + this.limit + " Populated Countries by Region\n");
        System.out.printf("%-10s %-25s %-20s %-20s %-20s %-10s\n",  "Code", "Name", "Continent", "Region" , "Population", "Capital");
        for(Country country: countries) {
            this.displayCountries(country);
        }
    }

    private void displayCountries(Country Country) {
        if(countries != null) {
            System.out.printf("%-10s %-25s %-20s %-20s %-20s %-10s\n",  Country.code, Country.name, Country.continent, Country.region, Country.population, Country.capital);
        }
    }



}
