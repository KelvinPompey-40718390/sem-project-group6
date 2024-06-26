package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.db.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class CountriesInWorld implements Report {

    private final Connection con;
    private Integer limit;
    public ArrayList<Country> countries;
    public CountriesInWorld() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        limit = Integer.parseInt(getInput());
        this.executeQuery();
        this.displayCountries();
    }

    public void runWithLimit(Integer limit) {
        this.limit = limit;
        this.executeQuery();
        this.displayCountries();
    }

    private String getInput() { return Utils.readInput("Enter Number of Countries to display, or 0 to Show All");   }

       private void executeQuery()
    {
        countries = new ArrayList<>();
        try
       {
            // Create an SQL statement
            Statement stmt = con.createStatement();

           String strSelect = "";
            // Create string for SQL statement

           if(this.limit > 0) {
               strSelect =
                       "select country.code, country.name as CountryName, continent, region, country.population,city.name as Capital\n" +
                               "from country\n" +
                               "inner join city on city.ID = country.Capital\n" +
                               "order by country.population desc " +
                                "LIMIT " + this.limit + ";";
           }

           else
           {
               strSelect =
                       "select country.code, country.name as CountryName, continent, region, country.population,city.name as Capital\n" +
                               "from country\n" +
                               "inner join city on city.ID = country.Capital\n" +
                               "order by country.population desc";
           }

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                Country country  = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("CountryName");
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

    public void displayCountries() {
        if(this.countries == null) {
            return;
        }

        System.out.println("All the Countries In the World: ");
        System.out.printf("%-5s %-40s %-20s %-30s %-20s %-30s\n", "CODE", "NAME","CONTINENT", "REGION", "POPULATION", "CAPITAL");
        for(Country country: countries) {
            this.displayCountry(country);
        }
    }

    private void displayCountry(Country country) {
        if(country != null) {
            System.out.printf("%-5s %-40s %-20s %-30s %-20s %-30s\n",  country.code, country.name,country.continent, country.region, country.population, country.capital);
        }
    }


}
