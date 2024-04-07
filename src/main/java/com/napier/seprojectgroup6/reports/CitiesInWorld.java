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
   public CitiesInWorld() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        this.executeQuery();
        this.displayCities();
    }

    public void runWithCities() {
        this.executeQuery();
        this.displayCities();
    }

       private void executeQuery()
    {
        cities = new ArrayList<>();
try
               {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name,country.Name AS CountryName,  city.District, city.Population\n" +
                            "FROM city\n" +
                            "         INNER JOIN country ON city.CountryCode = country.Code\n" +
                            "WHERE country.Continent\n" +
                            "ORDER BY city.Population DESC ";
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

        System.out.println("All the Cities In the World: ");
        System.out.printf("%-35s %-40s %-30s %-15s\n",  "City", "Country", "District", "Population");
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