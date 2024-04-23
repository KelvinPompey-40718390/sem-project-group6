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

            /**
             *  CITY REPORTS
             */

            //Cities in the World with and Without Limit - UC7 & UC12
            CitiesInWorld citiesInWorld = new CitiesInWorld();
            citiesInWorld.runWithInputs(5);
            citiesInWorld.runWithInputs(0);

            // Cities by Continent with and Without Limit - UC8 & UC13
            CitiesInContinent citiesInContinent = new CitiesInContinent();
            citiesInContinent.runWithContinentAndLimits(3,"Africa");

            // Cities by Region with and Without Limit - UC9 & UC14
            CitiesInRegion citiesInRegion = new CitiesInRegion();
            citiesInRegion.runWithRegionAndLimits(3,"Caribbean");

            // Cities by Country with and Without Limit - UC10 & UC15
            CitiesInCountry citiesInCountry = new CitiesInCountry();
            citiesInCountry.runWithCountryAndLimits(3,"North America");

            // Cities by District with and Without Limit - UC11 & UC16
            CitiesInDistrict citiesInDistrict = new CitiesInDistrict();
            citiesInDistrict.runWithDistrictandLimits(3, "Zuid-Holland");

            /**
             *  CAPITAL CITY REPORTS
             */
            // Populated Capital Cities by Continent with and without Limit - UC 18 & UC21
            PopulatedCapitalCitiesByContinent populatedCapitalCitiesByContinent = new PopulatedCapitalCitiesByContinent();
            populatedCapitalCitiesByContinent.runWithInputs(5,"Africa");
            populatedCapitalCitiesByContinent.runWithInputs(0,"Africa");

            // Populated Capital Cities by Region with and without Limit  - UC19 & UC22
            PopulatedCapitalCitiesByRegion populatedCapitalCitiesByRegion = new PopulatedCapitalCitiesByRegion();
            populatedCapitalCitiesByRegion.runWithInputs(5,"Caribbean");
            populatedCapitalCitiesByRegion.runWithInputs(0,"Caribbean");

            /**
             * POPULATION REPORTS
             **/

            // UC 26 The Population of the World Report
            WorldPopulation worldPopulation = new WorldPopulation();
            worldPopulation.run();

            // UC 27 Population of people living in and out of cities in each continent Report
            PopulationInEachContinent populationInEachContinent = new PopulationInEachContinent();
            populationInEachContinent.run();

            // UC 28 Population of people living in and out of cities in each region Report
            PopulationInEachRegion populationInEachRegion = new PopulationInEachRegion();
            populationInEachRegion.run();

            // UC 29 Population of people living in and out of Cities in each country Report
            TotalInCountry totalInCountry = new TotalInCountry();
            totalInCountry.run();

            // UC 30 Population of people living in and out of cities in each district report
            TotalInContinent totalInContinent = new TotalInContinent();
            totalInContinent.run();

            // UC 31 Population of people living in and out of cities in each City Report
            PopulationOfACity populationOfACity = new PopulationOfACity();
            populationOfACity.run();

            /**
             * LANGUAGE REPORT
             */

            // UC32 Population and % of Population that speak a Certain Language
            NumberOfPeopleSpeakingACertainLanguage numberOfPeopleSpeakingACertainLanguage = new NumberOfPeopleSpeakingACertainLanguage();
            numberOfPeopleSpeakingACertainLanguage.run();



            // Close Connection to Database
            ConnectionManager.getInstance().disconnect();
        }
    }
}
