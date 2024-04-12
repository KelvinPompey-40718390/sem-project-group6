package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.db.Population;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TopPopulatedCountriesInAContinent implements Report {

    private Connection con = null;
    public ArrayList<Population> populations;
    private Integer limit;
    public String continent;

    public TopPopulatedCountriesInAContinent() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        continent = this.getContinent();
        limit = Integer.parseInt(this.getInput());
        this.executeQuery();
        this.displayPopulations();
    }
    public void runWithInputs(Integer limit, String continent) {
        this.continent = continent;
        this.limit = limit;
        this.executeQuery();
        this.displayPopulations();
    }


    // Ask user for input to generate report.
    private String getInput() {
        return Utils.readInput("Enter number of Countries to display, or 0 to show all");
    }

    private String getContinent()
    {
        return Utils.readInput("Enter Name of Continent");
    }

    // Execute query with inputs provided
    private void executeQuery()
    {
        populations = new ArrayList<>();

        if(this.limit == null) {
            return;
        }
        try
        {
                // Create an SQL statement

                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect = "";
            if(this.limit > 0){
                strSelect = "select country.name as CountryName, continent, country.population as Population " +
                "from country\n" +
                "inner join city on city.ID = country.Capital\n" +
                "where continent = '" + this.continent + "' " +
                "order by country.population desc " +
                "Limit " + this.limit;

            }
            // If a 0 is entered return all the results of the Query
            else {
                strSelect = "select country.name as CountryName, continent, country.population as Population " +
                        "from country\n" +
                        "inner join city on city.ID = country.Capital\n" +
                        "where continent = '" + continent + "' " +
                        "order by country.population desc ";

            }


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                Population population = new Population();
                population.name = rset.getString("CountryName");
                population.continent = rset.getString("Continent");
                population.totalPopulation = rset.getLong("Population");


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

        System.out.println("\nTop Populated Countries In A Continent\n");
        System.out.printf("%-40s %-20s %-10s\n",  "Name", "Continent", "Population");
        for(Population population: populations) {
            this.displayPopulation(population);

        }
    }

    private void displayPopulation(Population population) {
        if(population != null) {
            System.out.printf("%-40s %-20s %-10s\n", population.name, population.continent, population.totalPopulation);

        }

    }

}
