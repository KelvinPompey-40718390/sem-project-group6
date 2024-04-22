package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.City;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/*
    @author Sekonya Phoka
 */
public class PopulationOfACity implements Report {

    private final Connection con;
    public String cityName;
    public City city;

    public PopulationOfACity() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        this.cityName = Utils.readInput("Enter city name");
        this.executeQuery();
        this.displayCities();
    }

    public void runWithLimit(Integer limit) {
        this.executeQuery();
        this.displayCities();
    }

    private void executeQuery()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = String.format("select Name, Population from city where Name = '%s'", cityName);
            System.out.println(strSelect);

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                this.city = new City();
                this.city.name = rset.getString("Name");
                this.city.population = rset.getInt("Population");
            }


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    public void displayCities() {
        if(this.city == null) {
            return;
        }

        System.out.println("Population Of A City\n");
        System.out.printf("%-20s %-10s\n",  "City", "Population");
        this.displayCity(city);

    }

    private void displayCity(City city) {
        if(city != null) {
            System.out.printf("%-20s %d\n",  city.name, city.population);
        }
    }


}
