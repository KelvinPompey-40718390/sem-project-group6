package com.napier.seprojectgroup6;


import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.navigation.Navigator;
import com.napier.seprojectgroup6.reports.*;

public class App {
    /**
     *
     * @param args Commandline arguments
     */
    public static void main(String[] args) {

        // Connect to database and instantiate Navigator
        // if running locally ie not on GitHub actions.

        System.out.println("SEM GROUP 6 PROJECT LOADED!");

        if(args.length < 1){
            ConnectionManager.getInstance().connect("localhost:33060", 0);
        }else{
            ConnectionManager.getInstance().connect(args[0], Integer.parseInt(args[1]));
        }

        boolean isRunningOnDocker = args.length > 1;
        if(!isRunningOnDocker) {
            new Navigator();
        }

        // Run all Reports automatically on Docker
        else {

            /**
             * COUNTRY REPORTS
             */

            // Initialize various classes for Country Reports
            CountriesInWorld countriesInWorld = new CountriesInWorld();
            TopPopulatedCountriesInAContinent topPopulatedCountriesInAContinent = new TopPopulatedCountriesInAContinent();
            PopulatedCountriesInARegion populatedCountriesInARegion = new PopulatedCountriesInARegion();

            // UC01 All Countries in the World
            countriesInWorld.runWithLimit(0);

            // UC02 All Countries in a Continent
            topPopulatedCountriesInAContinent.runWithInputs(0,"Africa");

            // UC03 All Countries in a Region
            populatedCountriesInARegion.runWithLimit(0,"Caribbean");

            // UC04 Top 3 Countries in the World
            countriesInWorld.runWithLimit(3);

            // UC05 Top 3 Countries in a Continent
            topPopulatedCountriesInAContinent.runWithInputs(3,"Africa");

            // UC06 Top 3 Countries in a Region
            populatedCountriesInARegion.runWithLimit(3,"Caribbean");

            /**
             *  CITY REPORTS
             */

            // Initialize Various classes for City Reports
            CitiesInWorld citiesInWorld = new CitiesInWorld();
            CitiesInContinent citiesInContinent = new CitiesInContinent();
            CitiesInRegion citiesInRegion = new CitiesInRegion();
            CitiesInCountry citiesInCountry = new CitiesInCountry();
            CitiesInDistrict citiesInDistrict = new CitiesInDistrict();

            // UC 07 All Cities in the World
            citiesInWorld.runWithInputs(0);

            // UC 08 All Cities in a Continent
            citiesInContinent.runWithContinentAndLimits(0,"Africa");

            // UC 09 All Cities in a Region
            citiesInRegion.runWithRegionAndLimits(0,"Caribbean");

            // UC 10 All Cities in a Country
            citiesInCountry.runWithCountryAndLimits(0,"North America");

            // UC 11 All Cities in a District
            citiesInDistrict.runWithDistrictandLimits(0, "Zuid-Holland");

            // UC 12 Top 3 populated Cities in the World
            citiesInWorld.runWithInputs(3);

            // UC 13 Top 3 Cities in a Continent
            citiesInContinent.runWithContinentAndLimits(3,"Africa");

            // UC 14 Top 3 Cities in a Region
            citiesInRegion.runWithRegionAndLimits(3,"Caribbean");

            // UC 15 Top 3 Cities in a Country
            citiesInCountry.runWithCountryAndLimits(3,"North America");

            // UC 16 Top 3 Cities in a District
            citiesInDistrict.runWithDistrictandLimits(3, "Zuid-Holland");

            /**
             *  CAPITAL CITY REPORTS
             */
            // Initial Various Classes for the Capital City Reports
            PopulatedCapitalCitiesByContinent populatedCapitalCitiesByContinent = new PopulatedCapitalCitiesByContinent();
            PopulatedCapitalCitiesByRegion populatedCapitalCitiesByRegion = new PopulatedCapitalCitiesByRegion();
            PopulatedCapitalCitiesinWorld populatedCapitalCitiesinWorld = new PopulatedCapitalCitiesinWorld();

            // UC 17 All the Capital Cities in the World
            populatedCapitalCitiesinWorld.runWithInputs(0);

            // UC 18 All the Capital Cities in a Continent Report
            populatedCapitalCitiesByContinent.runWithInputs(0,"Africa");

            // UC 19 All the Capital Cities in a Region Report
            populatedCapitalCitiesByRegion.runWithInputs(0,"Caribbean");

            // UC 20 Top 3 Populated Capital Cities in the World
            populatedCapitalCitiesinWorld.runWithInputs(3);

            // UC 21 Top 3 Capital Cities in a Continent Report
            populatedCapitalCitiesByContinent.runWithInputs(3,"Africa");

            // UC 22 Top 3 the Capital Cities in a Region Report
            populatedCapitalCitiesByRegion.runWithInputs(3,"Caribbean");


            /**
             * POPULATION REPORTS
             */

            // UC 23 Population of people living in and out of cities in each continent Report
            PopulationInEachContinent populationInEachContinent = new PopulationInEachContinent();
            populationInEachContinent.run();

            // UC 24 Population of people living in and out of cities in each region Report
            PopulationInEachRegion populationInEachRegion = new PopulationInEachRegion();
            populationInEachRegion.run();

            // UC 25 Population of people living in and out of Cities in each country Report
            PopulationInEachCountry populationInEachCountry = new PopulationInEachCountry();
            populationInEachCountry.run();

            /**
             * POPULATION TOTAL REPORTS
             **/

            // UC 26 The Population of the World Report
            WorldPopulation worldPopulation = new WorldPopulation();
            worldPopulation.run();

            // UC 27 Total Population of a Continent
            TotalInContinent totalInContinent = new TotalInContinent();
            totalInContinent.runWithInputs("Europe");

            // UC 28 Total Population of a Region
            TotalInRegion totalInRegion = new TotalInRegion();
            totalInRegion.runWithInputs("Caribbean");

            // UC 29 Total Population of a Country
            TotalInCountry totalInCountry = new TotalInCountry();
            totalInCountry.runWithInputs("France");

            // UC 30 Total Population of a District
            TotalInDistrict totalInDistrict = new TotalInDistrict();
            totalInDistrict.runWithInputs("Aichi");

            // UC 31 Total Population of a City
            PopulationOfACity populationOfACity = new PopulationOfACity();
            populationOfACity.runWithCity("Paris");

            /**
             * LANGUAGE REPORT
             */

            // UC 32 Population and % of Population that speak a Certain Language
            NumberOfPeopleSpeakingACertainLanguage numberOfPeopleSpeakingACertainLanguage = new NumberOfPeopleSpeakingACertainLanguage();
            numberOfPeopleSpeakingACertainLanguage.run();


            // Close Connection to Database
            ConnectionManager.getInstance().disconnect();
        }
    }
}
