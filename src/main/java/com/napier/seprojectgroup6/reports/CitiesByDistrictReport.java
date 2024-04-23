package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.City;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CitiesByDistrictReport implements Report {

    private final Connection con;
    public ArrayList<City> cities;
    private String district;


    public CitiesByDistrictReport() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public boolean run() {
        district = this.getInput();
        this.executeQuery();
        this.displayCities();
        return true;
    }

    public void runWithDistrict(String district) {
        this.district = district;
        this.executeQuery();
        this.displayCities();
    }

    private String getInput() {
        return Utils.readInput("Enter district");
    }

    private void executeQuery()
    {
        cities = new ArrayList<>();

        if(this.district.isEmpty()) {
            return;
        }
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = String.format("select ID, city.Name, country.Name, CountryCode, District, city.Population from city left join country on city.CountryCode = country.Code where District = '%s' order by city.Population desc", this.district);

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                City city = new City();
                city.name = rset.getString("Name");
                city.id = rset.getInt("ID");
                city.population = rset.getInt("Population");
                city.countryCode = rset.getString("CountryCode");
                city.countryName = rset.getString("country.Name");
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

        System.out.println("\nCities by District: " + district + "\n");
        System.out.printf("%-10s %-10s %-10s %-10s\n",  "NAME", "COUNTRY", "DISTRICT", "POPULATION");
        for(City city: cities) {
            this.displayCity(city);
        }
        System.out.println();
    }
    private void displayCity(City city) {
        if(city != null) {
            System.out.printf("%-10s %-10s %-10s %-10s\n",  city.name, city.countryName, city.district, city.population);
        }
    }


}
