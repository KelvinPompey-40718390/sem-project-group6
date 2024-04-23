package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.db.Population;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class NumberOfPeopleSpeakingACertainLanguage implements Report{
    private final Connection con;
    public ArrayList<Population> populations;

    public NumberOfPeopleSpeakingACertainLanguage() {
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

            String strSelect =
                    "SELECT CL.Language, SUM(C.Population) AS Language_Speakers, " +
                    "CONCAT(ROUND((SUM(C.Population)/ (SELECT SUM(country.Population) FROM country)*100),2),'%') as PCT_World_Population " +
                    "FROM country AS C " +
                    "LEFT JOIN countrylanguage as CL ON C.Code = CL.CountryCode " +
                    "WHERE CL.Language IN('Chinese', 'English', 'Hindi', 'Spanish','Arabic') " +
                    "GROUP BY CL.Language " +
                    "ORDER BY SUM(C.Population) DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                Population population = new Population();
                population.name = rset.getString("language");
                population.totalPopulation = rset.getLong("Language_Speakers");
                population.percentageOfWorldPopulation = rset.getString("PCT_World_Population");

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

        System.out.println("\nTop Languages By Population\n");
        System.out.printf("%-10s %-20s %-10s\n",  "LANGUAGE", "NUMBER OF PEOPLE", "PERCENTAGE OF WORLD POPULATION");
        for(Population population: populations) {
            this.displayPopulation(population);
        }
    }

    private void displayPopulation(Population population) {
        if(population != null) {
            System.out.printf("%-10s %-20s %-10s\n", population.name, population.totalPopulation, population.percentageOfWorldPopulation);
        }
    }
}