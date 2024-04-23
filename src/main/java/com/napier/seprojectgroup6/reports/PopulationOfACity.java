package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.InputReader;
import com.napier.seprojectgroup6.db.City;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/*
    @author Sekonya Phoka
 */
public class PopulationOfACity implements Report {

    private final Connection con = ConnectionManager.getInstance().getConnection();
    public String cityName;
    public City city;
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
        this.cityName = inputReader.readInput("Enter city name");
        this.city = this.executeQuery();
        return this.displayCity(this.city);

    }

    public boolean runWithCity(String name) {
        this.cityName = name;
        this.city = this.executeQuery();
        return this.displayCity(this.city);
    }

    public City executeQuery()
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

            City city = new City();
            while (rset.next())
            {
                city = new City();
                city.name = rset.getString("Name");
                city.population = rset.getInt("Population");
            }

            return city;


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public boolean displayCity(City city) {
        if(city != null) {
            System.out.println("Population Of A City\n");
            System.out.printf("%-20s %-10s\n",  "City", "Population");
            System.out.printf("%-20s %d\n",  city.name, city.population);
            return true;
        }
        return false;
    }


}
