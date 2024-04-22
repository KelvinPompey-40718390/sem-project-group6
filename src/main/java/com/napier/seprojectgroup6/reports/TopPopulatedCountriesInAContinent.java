package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.db.Population;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TopPopulatedCountriesInAContinent implements Report {

    private final Connection con;
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

            Statement stmt = con.createStatement();// Create string for SQL statement
            // If a 0 is entered return all the results of the Query
            String strSelect = "select country.name as CountryName, continent, country.population as Population " +
                    "from country\n" +
                    "inner join city on city.ID = country.Capital\n" +
                    "where continent = '" + continent + "' " +
                    "order by country.population desc ";

            if(this.limit > 0){
                strSelect = "select country.code as Code, country.name as CountryName, continent, region, country.population, city.name as Capital\n" +
                "from country\n" +
                "inner join city on city.ID = country.Capital\n" +
                "where continent = '" + this.continent + "' " +
                "order by country.population desc " +
                "Limit " + this.limit;

            }
            // If a 0 is entered return all the results of the Query
            else {
                strSelect = "select country.code as Code, country.name as CountryName, continent, region, country.population, city.name as Capital\n" +
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
                population.code = rset.getString("Code");
                population.name = rset.getString("CountryName");
                population.continent = rset.getString("Continent");
                population.region = rset.getString("Region");
                population.totalPopulation = rset.getLong("Population");
                population.capital = rset.getString("Capital");


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

        System.out.println("\nCountries In A Continent:" + continent+ "\n");
        System.out.printf("%-10s %-40s %-20s %-30s %-20s %-20s\n", "CODE", "NAME", "CONTINENT", "REGION", "POPULATION", "CAPITAL");
        for(Population population: populations) {
            this.displayPopulation(population);

        }
    }

    private void displayPopulation(Population population) {
        if(population != null) {
            System.out.printf("%-10s %-40s %-20s %-30s %-20s %-20s\n",population.code, population.name, population.continent,population.region, population.totalPopulation, population.capital);

        }

    }

}
