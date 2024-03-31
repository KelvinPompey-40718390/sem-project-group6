package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.db.Population;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PopulationInEachCountry implements Report {

    private Connection con = null;
    public ArrayList<Population> populations;

    public PopulationInEachCountry() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        this.executeQuery();
        this.displayPopulations();
    }

    // Execute query with inputs provided
    private void executeQuery()
    {
        populations = new ArrayList<>();

        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "";

            strSelect = "SELECT country.Name AS Name, SUM(country.Population) AS Population, " +
                                "IFNULL(CONCAT(ROUND((SUM(city.Population)/SUM(country.Population)) * 100,2), '%'),'0.00%') AS InCityPct," +
                                "IFNULL(CONCAT(ROUND(((SUM(country.Population) - SUM(city.Population))/SUM(country.Population)) * 100,2),'%'),'0.00%') AS OutCityPct " +
                        "FROM country " +
                        "LEFT JOIN city ON country.Capital = city.ID " +
                        "GROUP BY country.Name";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                Population population = new Population();
                population.name = rset.getString("Name");
                population.totalPopulation = rset.getLong("Population");
                population.pctLivingInCities = rset.getString("InCityPct");
                population.pctNotLivingInCities = rset.getString("OutCityPct");

                this.populations.add(population);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    public void displayPopulations() {
        if(this.populations == null) {
            return;
        }

        System.out.println("\nThe population of people, people living in cities, and people not living in cities in each Country\n");
        System.out.printf("%-40s %-20s %-10s %10s\n",  "COUNTRY", "POPULATION", "IN CITY", "OUT CITY");
        for(Population population: populations) {
            this.displayPopulation(population);

        }
    }

    private void displayPopulation(Population population) {
        if(population != null) {
            System.out.printf("%-40s %-20s %-10s %10s\n", population.name, population.totalPopulation, population.pctLivingInCities, population.pctNotLivingInCities);

        }

    }

}
