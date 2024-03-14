package com.napier.seprojectgroup6.reports;

import com.napier.seprojectgroup6.Utils;

public class CountriesByContinentReport implements Report{
    public void run() {
        this.getInput();
        this.executeQuery();
    }
    private void getInput() {
        String continent = Utils.readInput("Enter Continent");
        System.out.println("Continent is " + continent);
    }

    private void executeQuery() {
        System.out.println("Execute query for countries by continent\n");
    }
}
