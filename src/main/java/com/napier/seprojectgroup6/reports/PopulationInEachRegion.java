package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.db.Population;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PopulationInEachRegion implements Report {

    private Connection con = null;
    public ArrayList<Population> populations;

    // Establish connection to Database
    public PopulationInEachRegion() {
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

            strSelect = "SELECT country.Region AS Name, SUM(country.Population) AS Population, " +
                    "IFNULL(CONCAT(ROUND((SUM(city.Population)/SUM(country.Population)) * 100,2), '%'),'0.00%') AS InCityPct," +
                    "IFNULL(CONCAT(ROUND(((SUM(country.Population) - SUM(city.Population))/SUM(country.Population)) * 100,2),'%'),'0.00%') AS OutCityPct " +
                    "FROM country " +
                    "LEFT JOIN city ON country.Capital = city.ID " +
                    "GROUP BY country.Region";

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

    // Prepare Header Rows for Query Results
    public void displayPopulations() {
        if(this.populations == null) {
            return;
        }

        System.out.println("\nThe population of people, people living in cities, and people not living in cities in each Region\n");
        System.out.printf("%-30s %-20s %-10s %10s\n",  "REGION", "POPULATION", "IN CITY", "OUT CITY");
        for(Population population: populations) {
            this.displayPopulation(population);

        }
    }

    // Display Results of the Query
    private void displayPopulation(Population population) {
        if(population != null) {
            System.out.printf("%-30s %-20s %-10s %10s\n", population.name, population.totalPopulation, population.pctLivingInCities, population.pctNotLivingInCities);

        }

    }

}
