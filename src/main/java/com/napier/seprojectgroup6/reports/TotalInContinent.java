/**
 * Use Case 27
 */

package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TotalInContinent implements Report {

    private Connection con = null;
    public ArrayList<TotalInContinent> totalInContinent;
    public long TotalInContinent;
    private String continent;

    public TotalInContinent() { this.con = ConnectionManager.getInstance().getConnection();}

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        continent = this.getContinent();
        this.executeQuery();
        this.displayTotalInContinent();
    }

    //For Testing Application
    public void runWithInputs(String continent) {
        this.continent = continent;
        this.executeQuery();
        this.displayTotalInContinent();
    }

    // Request continent from user to generate report.
    private String getContinent()
    {
        return Utils.readInput("Enter Name of Continent");
    }

    // Execute query when input is provided
    private void executeQuery() {
        totalInContinent = new ArrayList<>();

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "";

            strSelect = "SELECT SUM(Population) AS Population FROM world.country WHERE Continent = 'Europe'";
            //strSelect = "SELECT SUM(Population) AS ContinentPop FROM  world.country AS c JOIN world.city AS pop On c.countrycode = pop.code WHERE c = 'Africa' ";

                    //Execute SQL statement
                    ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                TotalInContinent = rset.getLong("Population");

                // this.worldPopulation.add(worldPopulation);
            }
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    public void displayTotalInContinent() {
        if(this.totalInContinent == null) {
            return;
        }

        System.out.println("Population of the Continent:");
        System.out.println(TotalInContinent);
    }

    private void displayTotalInContinent (TotalInContinent totalInContinent) {
        if (totalInContinent != null) {
            System.out.printf("%d", this.TotalInContinent);
        }
    }
}