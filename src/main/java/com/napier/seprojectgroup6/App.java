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
            totalInContinent.run();

            // UC 28 Total Population of a Region
            TotalInRegion totalInRegion = new TotalInRegion();
            totalInRegion.run();

            // UC 29 Total Population of a Country
            TotalInCountry totalInCountry = new TotalInCountry();
            totalInCountry.run();

            // UC 30 Total Population of a District
            TotalInDistrict totalInDistrict = new TotalInDistrict();
            totalInDistrict.run();

            // UC 31 Total Population of a City
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
