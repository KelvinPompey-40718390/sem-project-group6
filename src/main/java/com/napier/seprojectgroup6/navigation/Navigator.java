package com.napier.seprojectgroup6.navigation;

import com.napier.seprojectgroup6.Utils;
import com.napier.seprojectgroup6.reports.*;

public class Navigator {

    // List of menu Items for user Navigation
    private final ReportMenu[] reports = {

            // Country Reports UC01 - UC 06
            new ReportMenu("Top N and All populated Countries in the World", CountriesInWorld.class.getName()),
            new ReportMenu("Top N and All populated Countries in a Continent", TopPopulatedCountriesInAContinent.class.getName()),
            new ReportMenu("Top N and All populated Countries in a Region", PopulatedCountriesInARegion.class.getName()),

            // City Reports UC07 - UC16
            new ReportMenu( "Top N and All Populated Cities in the World", CitiesInWorld.class.getName()),
            new ReportMenu( "Top N and All Populated Cities in a Continent", CitiesInContinent.class.getName()),
            new ReportMenu( "Top N and All Populated Cities in a Region", CitiesInRegion.class.getName()),
            new ReportMenu( "Top N and All Populated Cities in a Country", CitiesInCountry.class.getName()),
            new ReportMenu( "Top N and All Populated Cities in a District", CitiesInDistrict.class.getName()),

            // Capital City Reports UC17 - UC22
            new ReportMenu( "Top N and All Populated Capital Cities in the World", PopulatedCapitalCitiesinWorld.class.getName()),
            new ReportMenu( "Top N and All Populated Capital Cities in a Continent", PopulatedCapitalCitiesByContinent.class.getName()),
            new ReportMenu( "Top N and All Populated Capital Cities in a Region", PopulatedCapitalCitiesByRegion.class.getName()),

            // Population Reports UC23 - UC25
            new ReportMenu( "Percentage of Population Living in/out of city in each Continent", PopulationInEachContinent.class.getName()),
            new ReportMenu( "Percentage of Population Living in/out of city in each Region", PopulationInEachRegion.class.getName()),
            new ReportMenu( "Percentage of Population Living in/out of city in each Country", PopulationInEachCountry.class.getName()),

            // Total Population Reports UC26 - UC31
            new ReportMenu( "Total Population of the World", WorldPopulation.class.getName()),
            new ReportMenu( "Total Population of a Continent", TotalInContinent.class.getName()),
            new ReportMenu( "Total Population of a Region", TotalInRegion.class.getName()),
            new ReportMenu( "Total Population of a Country", TotalInCountry.class.getName()),
            new ReportMenu( "Total Population of a District", TotalInDistrict.class.getName()),
            new ReportMenu( "Total Population of a City", PopulationOfACity.class.getName()),

            // Language Report UC32
            new ReportMenu( "Percentage of World speaking different languages", NumberOfPeopleSpeakingACertainLanguage.class.getName())
    };

    // Show navigator Menu
    public Navigator() {

        this.showMenu();
    }

    // Show Report menu Header and Menu Items
    private void showMenu() {
        System.out.println();
        System.out.println("-----------");
        System.out.println("REPORT MENU");
        System.out.println("-----------");
        for(int i = 0; i < this.reports.length; i++) {
            System.out.println(String.format("%02d", i) + ": " + this.reports[i].title);
        }
        this.processInput();
    }

    // Ask user to enter a Report Number or enter a 'q' to Quite the application
    private void processInput() {
        String input = Utils.readInput("\nSelect your report number or q to quit!");

        // Test for both lowercase and uppercase Q
        if(input.equalsIgnoreCase("Q")) {
            return;
        }

        // Is the string is not numeric reject the input and try again
        String regex = "-?\\d+";
        boolean isNumber = input.matches(regex);

        if(!isNumber) {
            System.out.println("Not a valid number");
            this.processInput();
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
     * Display the report selected by the user
     * @param index Selected report to display
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

