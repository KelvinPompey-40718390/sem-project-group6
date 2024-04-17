/**
 * This Reports covers Use Case 19 and 22
 */

package com.napier.seprojectgroup6.reports;
import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.CapitalCity;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AllCapitalCitiesbyRegion implements Report {

    private Connection con = null;
    public ArrayList<CapitalCity> capitalCities;
    private String region;


    public AllCapitalCitiesbyRegion() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        region = this.getInput();
        this.executeQuery();
        this.displayCapitalCities();
    }

    public void runWithRegion(String region) {
        this.region = region;
        this.displayCapitalCities();
    }

    private String getInput(){
        return Utils.readInput("Enter Region");
    }

    // Execute query when input is provided
    private void executeQuery()
    {
        capitalCities = new ArrayList<>();

        if(this.region.isBlank()){
                throw new IllegalArgumentException("Please enter a Region.");
            }

        else try

        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "";

                strSelect = "SELECT city.name AS CityName, country.name AS CountryName, city.Population " +
                            "FROM city " +
                            "INNER JOIN country ON country.Code = city.CountryCode " +
                            "WHERE country.Region = '"+region+"'" +
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


        System.out.println("All CAPITAL CITIES BY REGION: ");
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
