package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.InputReader;
import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.db.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TopPopulatedCountries implements Report {

    private final Connection con = ConnectionManager.getInstance().getConnection();;
    public ArrayList<Country> countries;
    private Integer limit;

    private String region;

    private InputReader inputReader = new InputReader();

    public InputReader getInputReader() {
        return this.inputReader;
    }

    public void setInputReader(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public boolean run() {
        limit = Integer.parseInt(this.getInput());
        region = this.getRegion();
        this.executeQuery();
        this.displayCountries();
        return true;
    }


    public boolean runWithLimit(Integer limit,String region) {
        this.limit = limit;
        this.region =region;
        this.executeQuery();
        this.displayCountries();
        return true;
    }

    private String getInput() {
        return inputReader.readInput("Enter N for the number of countries to display, or 0 to Show All");
    }

    private String getRegion()
    {
        return inputReader.readInput("Enter Name of Region");
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
                strSelect = "select country.code, country.Name, country.Continent,country.Region, country.Population, country.Capital from country\n" +
                        "WHERE country.Region = '" + this.region +"' " +
                        "order by Population desc ";
            }


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getString("Capital");

                this.countries.add(country);
            }


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    public boolean displayCountries() {
        if(this.countries == null) {
            return false;
        }

        System.out.println("Top " + this.limit + " Populated Countries\n");
        System.out.printf("%-10s %-25s %-20s %-20s %-20s %-10s\n",  "CODE", "NAME", "CONTINENT", "REGION" , "POPULATION", "CAPITAL");
        for(Country country: countries) {
            this.displayCountries(country);
        }

        return true;
    }

    private boolean displayCountries(Country country) {
        if(countries != null) {
            System.out.printf("%-10s %-25s %-20s %-20s %-20s %-10s\n",  country.code, country.name, country.continent, country.region, country.population, country.capital);
            return true;
        }
        return false;
    }



}
