package com.napier.seprojectgroup6;

import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.navigation.Navigator;
import com.napier.seprojectgroup6.reports.*;

public class App {
    /**
     *
     * @param args
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
            Navigator navigator = new Navigator();
        }
        else {

            /**
             *
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
             *  CITY REPORTS
             */

            //Cities in the World with and Without Limit - UC7 & UC12
            CitiesInWorld citiesInWorld = new CitiesInWorld();
            citiesInWorld.runWithInputs(5);
            citiesInWorld.runWithInputs(0);

            // Cities by Continent with and Without Limit - UC8 & UC13
            CitiesInContinent citiesInContinent = new CitiesInContinent();
            citiesInContinent.runWithContinentandLimits(3,"Africa");

            // Cities by Region with and Without Limit - UC9 & UC14
            CitiesInRegion citiesInRegion = new CitiesInRegion();
            citiesInRegion.runWithRegionandLimits(3,"Caribbean");

            // Cities by Country with and Without Limit - UC10 & UC15
            CitiesInCountry citiesInCountry = new CitiesInCountry();
            citiesInCountry.runWithCountryandLimits(3,"North America");

            // Cities by District with and Without Limit - UC11 & UC16
            CitiesInDistrict citiesInDistrict = new CitiesInDistrict();
            citiesInDistrict.runWithDistrictandLimits(3, "Zuid-Holland");

            /**
             * COUNTRIES REPORTS
             */

            //Top Populated Countries By Region - UC6
            TopPopulationCountriesRegion topPopulationCountriesRegion = new TopPopulationCountriesRegion();
            topPopulationCountriesRegion.runWithLimit(10, "Caribbean");

            /**
             * POPULATION REPORTS
             */

            // Population of people living in and out of cities in each continent - UC 23
            PopulationInEachContinent populationInEachContinent = new PopulationInEachContinent();
            populationInEachContinent.run();

            // Population of people living in and out of cities in each region - UC 24
            PopulationInEachRegion populationInEachRegion = new PopulationInEachRegion();
            populationInEachRegion.run();


            // Close Connection to Database
            ConnectionManager.getInstance().disconnect();
        }
    }
}
