package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.db.City;
import com.napier.seprojectgroup6.db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CitiesByDistrictReport implements Report {

    private Connection con = null;


    public CitiesByDistrictReport() {
        this.con = ConnectionManager.getInstance().getConnection();
    }

    /**
     * Request input from the user and
     * execute the query
     */
    public void run() {
        String district = this.getInput();
        this.executeQuery(district);
    }

    public void runWithDistrict(String distict) {
        this.executeQuery(distict);
    }

    private String getInput() {
        return Utils.readInput("Enter district");
    }

    private void executeQuery(String district)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select ID, Name, CountryCode, District, Population from city " +
                            "where District = '"+ district +"' order by Population desc;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned

            System.out.println("Cities by District: " + district + "\n");
            System.out.printf("%-10s %-10s %-10s %-10s\n",  "City", "ID", "Population", "CountryCode");
            while (rset.next())
            {

                City city = new City();
                city.name = rset.getString("Name");
                city.ID = rset.getInt("ID");
                city.population = rset.getInt("Population");
                city.countryCode = rset.getString("CountryCode");
                city.district = rset.getString("District");

                this.displayCity(city);
            }


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
        }
    }

    public void displayCity(City city) {
        if(city != null) {
            System.out.printf("%-10s %-10s %-10s %-10s\n",  city.name, city.ID, city.population, city.countryCode);
        }
    }


}
