/**
 * Use Case 26
 */

package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.CapitalCity;
import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.db.Population;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class WorldPopulation implements Report {

    private Connection con = null;
    public ArrayList<WorldPopulation> worldPopulation;
    public long WorldPopulation;

    public WorldPopulation() { this.con = ConnectionManager.getInstance().getConnection();}

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        this.executeQuery();
        this.displayWorldPopulation();
    }


    // Execute query when input is provided
    private void executeQuery() {
        worldPopulation = new ArrayList<>();

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "";

            strSelect = "SELECT SUM(Population) AS Population FROM country";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

           while (rset.next())
            {
               // WorldPopulation worldPopulation = new WorldPopulation();
               // worldPopulation.WorldPopulation = rset.getLong("Population");
                WorldPopulation = rset.getLong("Population");

                // this.worldPopulation.add(worldPopulation);
            }
        }

        catch (Exception e)
    {
        System.out.println(e.getMessage());
        System.out.println("Failed to get population details");
    }
}

public void displayWorldPopulation() {
    if(this.worldPopulation == null) {
        return;
    }

    System.out.println("Population of the World:");
    //System.out.printf("%-30s","POPULATION");
    //for(WorldPopulation worldPopulation: worldPopulation) {
    //    this.displayWorldPopulation();
    //}
    System.out.println(WorldPopulation);
}

    private void displayWorldPopulation (WorldPopulation worldPopulation) {
        if (worldPopulation != null) {
            System.out.printf("%d", this.WorldPopulation);
        }
    }
    }