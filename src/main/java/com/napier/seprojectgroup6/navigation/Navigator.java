package com.napier.seprojectgroup6.navigation;

import com.napier.seprojectgroup6.Utils;
<<<<<<< HEAD
import com.napier.seprojectgroup6.reports.Report;
import com.napier.seprojectgroup6.reports.CitiesByDistrictReport;
import com.napier.seprojectgroup6.reports.TopPopulatedCitiesByContinentReport;
import com.napier.seprojectgroup6.reports.TopPopulatedCitiesReport;
=======
import com.napier.seprojectgroup6.reports.*;
>>>>>>> origin/develop

public class Navigator {

    private final ReportMenu[] reports = {
<<<<<<< HEAD
            new ReportMenu("UC11 - Cities By District", CitiesByDistrictReport.class.getName()),
            new ReportMenu( "UC12 - Top Cities by Population", TopPopulatedCitiesReport.class.getName()),
            new ReportMenu("UC13 - Top N cities in continent", TopPopulatedCitiesByContinentReport.class.getName())
=======
            new ReportMenu("Cities By District", CitiesByDistrictReport.class.getName()),
            new ReportMenu( "Top Cities by Population", TopPopulatedCitiesReport.class.getName()),
            new ReportMenu( "06 Top Populated Countries By Region", TopPopulationCountriesRegion.class.getName()),
            new ReportMenu( "21 Populated Capital Cities by Continent", PopulatedCapitalCitiesByContinent.class.getName()),
            new ReportMenu( "22 Populated Capital Cities by Region", PopulatedCapitalCitiesByRegion.class.getName()),
            new ReportMenu( "23 Percent of Population Living in/out of city by Continent", PopulationInEachContinent.class.getName()),
            new ReportMenu( "24 Percent of Population Living in/out of city by Region", PopulationInEachRegion.class.getName()),
            new ReportMenu( "25 Percent of Population Living in/out of city by Country", PopulationInEachCountry.class.getName())

>>>>>>> origin/develop
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

