package com.napier.seprojectgroup6.navigation;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.reports.PopulatedCapitalCitiesByContinent;
import com.napier.seprojectgroup6.reports.Report;
import com.napier.seprojectgroup6.reports.CitiesByDistrictReport;
import com.napier.seprojectgroup6.reports.TopPopulatedCitiesReport;

public class Navigator {

    private final ReportMenu[] reports = {
            new ReportMenu("Cities By District", CitiesByDistrictReport.class.getName()),
            new ReportMenu( "Top Cities by Population", TopPopulatedCitiesReport.class.getName()),
            new ReportMenu( "Populated Capital Cities by Continent", PopulatedCapitalCitiesByContinent.class.getName())
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

