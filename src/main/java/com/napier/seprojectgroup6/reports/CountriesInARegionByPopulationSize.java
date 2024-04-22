package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.db.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CountriesInARegionByPopulationSize implements Report {

    private final Connection con;
    public ArrayList<Country> countries;
   public CountriesInARegionByPopulationSize() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        this.executeQuery();
        this.displayCountries();
    }

    public void runWithCities() {
        this.executeQuery();
        this.displayCountries();
    }

       private void executeQuery()
    {
        countries = new ArrayList<>();
try
               {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select country.name as CountryName, region, country.population\n" +
                            "from country\n" +
                            "inner join city on city.ID = country.Capital\n" +
                            "order by country.population desc;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                Country country  = new Country();
                country.name = rset.getString("CountryName");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");



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

        System.out.println("Countries In A Region By Population Size:");
        System.out.printf("\"%-35s %-40s %-30s  \n", "name", "Region", "population");
        for(Country country: countries) {
            this.displayCountry(country);
        }
    }

    private void displayCountry(Country country) {
        if(country != null) {
            System.out.printf("%-35s %-40s %-30s  \n", country.name, country.region, country.population);
        }
    }


}
