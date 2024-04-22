/**
 * This Reports covers Use Case 17
 */

package com.napier.seprojectgroup6.reports;
import com.napier.seprojectgroup6.db.CapitalCity;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AllCapitalCitiesWorld implements Report {

    private final Connection con;
    public ArrayList<CapitalCity> capitalCities;

    public AllCapitalCitiesWorld() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        this.executeQuery();
        this.displayCapitalCities();
    }


    // Execute query when input is provided
    private void executeQuery()
    {
        capitalCities = new ArrayList<>();

        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement


                String strSelect = "SELECT city.name AS CityName, country.name AS CountryName, city.Population " +
                            "FROM city " +
                            "INNER JOIN country ON country.Code = city.CountryCode " +
                            "ORDER BY city.Population Desc ";

                   // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                CapitalCity capitalCity = new CapitalCity();
                capitalCity.name = rset.getString("CityName");
                capitalCity.country = rset.getString("CountryName");
                capitalCity.population = rset.getInt("Population");

                this.capitalCities.add(capitalCity);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }

    public void displayCapitalCities() {
        if(this.capitalCities == null) {
            return;
        }
        //General Structure of Print output, All Uppercase
        System.out.println("ALL POPULATED CAPITAL CITIES IN THE WORLD:");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-30s %-40s %-10s\n",  "NAME", "COUNTRY", "POPULATION");
        for(CapitalCity capitalCity: capitalCities) {
            this.displayCapitalCity(capitalCity);
        }
    }

    private void displayCapitalCity(CapitalCity capitalCity) {
        if(capitalCity != null) {
            System.out.printf("%-30s %-40s %-10s \n",  capitalCity.name, capitalCity.country, capitalCity.population);
        }
    }

}
