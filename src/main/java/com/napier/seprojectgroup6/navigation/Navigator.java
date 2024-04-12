package com.napier.seprojectgroup6.navigation;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.reports.*;

public class Navigator {

    private final ReportMenu[] reports = {
            new ReportMenu("Cities By District", CitiesByDistrictReport.class.getName()),
            new ReportMenu( "Top Cities by Population", TopPopulatedCitiesReport.class.getName()),
            new ReportMenu( "06 Top Populated Countries By Region", TopPopulationCountriesRegion.class.getName()),
            new ReportMenu( "07 All Cities In The World", CitiesInWorld.class.getName()),
            new ReportMenu( "08 All Cities In a Continent", CitiesInContinent.class.getName()),
            new ReportMenu( "09 All Cities In A Region", CitiesInRegion.class.getName()),
            new ReportMenu( "10 All Cities by Country", CitiesInCountry.class.getName()),
            new ReportMenu( "21 Populated Capital Cities by Continent", PopulatedCapitalCitiesByContinent.class.getName()),
            new ReportMenu( "22 Populated Capital Cities by Region", PopulatedCapitalCitiesByRegion.class.getName()),
            new ReportMenu( "23 Percent of Population Living in/out of city by Continent", PopulationInEachContinent.class.getName()),
            new ReportMenu( "24 Percent of Population Living in/out of city by Region", PopulationInEachRegion.class.getName()),
            new ReportMenu( "25 Percent of Population Living in/out of city by Country", PopulationInEachCountry.class.getName()),
            new ReportMenu( "27 Total Population of a Continent", TotalInContinent.class.getName()),
            new ReportMenu( "28 Total Population of a Region", TotalInRegion.class.getName()),
            new ReportMenu( "29 Total Population of a country", TotalInCountry.class.getName()),
            new ReportMenu( "30 Total Population of a District", TotalInDistrict.class.getName())

    };

    public Navigator() {

        this.showMenu();
    }

    /**
     * Display the reports available
     */
    private void showMenu() {

        for(int i = 0; i < this.reports.length; i++) {
            System.out.println(i + ": " + this.reports[i].title);
        }
        this.processInput();
    }

    /**
     * Handle user input
     */
    private void processInput() {
        String input = Utils.readInput("\nSelect your report number or q to quit!");

        if(input.equals("q")) {
            return;
        }
        int index = Integer.parseInt(input);

        if(index > this.reports.length || index < 0)  {
            System.out.println("Report not found");
            this.processInput();
        }

        this.runRport(index);
        this.showMenu();
    }

    /**
     * Display the report selected by
     * the user
     * @param index
     */
    private void runRport(int index) {
        try {
            Report report = (Report) Class.forName(this.reports[index].className).getDeclaredConstructor().newInstance();
            report.run();
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Report not found!");
        }
        catch(Exception ex) {
            System.out.println("There was a problem instantiating the class");
        }
    }
}

