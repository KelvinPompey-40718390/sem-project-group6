/**
 * Use Case 26
 */

package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class WorldPopulation implements Report {

    private final Connection con;
    public ArrayList<WorldPopulation> worldPopulation;
    public long total;

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

                String strSelect = "SELECT SUM(Population) AS Population FROM country";
                //Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);

               while (rset.next())
                {
                    total = rset.getLong("Population");
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

        System.out.println(total);
    }
}