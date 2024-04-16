package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.db.Population;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PercentageOfWorldPopulation implements Report {

    private Connection con = null;
    public ArrayList<Population> populations;

    public PercentageOfWorldPopulation() {
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

            strSelect = "SELECT\n" +
                    "    cl.language,\n" +
                    "    SUM(cl.percentage * 0.01 * c.population) AS number_of_people,\n" +
                    "    ROUND((SUM(cl.percentage * 0.01 * c.population) / (SELECT SUM(population) FROM country)), 2) AS percentage_of_world_population\n" +
                    "FROM\n" +
                    "    countrylanguage cl\n" +
                    "        JOIN\n" +
                    "    country c ON cl.countrycode = c.code\n" +
                    "WHERE\n" +
                    "    cl.language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic')\n" +
                    "GROUP BY\n" +
                    "    cl.language\n" +
                    "ORDER BY\n" +
                    "    number_of_people ASC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                Population population = new Population();
                population.name = rset.getString("Name");
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

        System.out.println("\nPercentage Of World Population\n");
        System.out.printf("%-40s %-20s\n",  "COUNTRY", "POPULATION");
        for(Population population: populations) {
            this.displayPopulation(population);

        }
    }

    private void displayPopulation(Population population) {
        if(population != null) {
            System.out.printf("%-40s %-20s\n", population.name, population.totalPopulation);

        }

    }

}
