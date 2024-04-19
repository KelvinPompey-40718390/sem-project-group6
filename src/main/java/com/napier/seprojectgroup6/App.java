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
             *  CAPITAL CITY REPORTS
             */
            // Populated Capital Cities by Continent with and without Limit
            PopulatedCapitalCitiesByContinent populatedCapitalCitiesByContinent = new PopulatedCapitalCitiesByContinent();
            populatedCapitalCitiesByContinent.runWithInputs(5,"Africa");
            populatedCapitalCitiesByContinent.runWithInputs(0,"Africa");

            // Populated Capital Cities by Region with and without Limit
            PopulatedCapitalCitiesByRegion populatedCapitalCitiesByRegion = new PopulatedCapitalCitiesByRegion();
            populatedCapitalCitiesByRegion.runWithInputs(5,"Caribbean");
            populatedCapitalCitiesByRegion.runWithInputs(0,"Caribbean");

            /**
             *  CITY REPORTS
             */

            // Populated Cities by Country
            CitiesInCountry citiesInCountry = new CitiesInCountry();
            citiesInCountry.runWithCountry("North America");

            // Populated Cities by District
            CitiesByDistrictReport citiesByDistrictReport = new CitiesByDistrictReport();
            citiesByDistrictReport.runWithDistrict("Aichi");

            //Cities in the World with and Without Limit
            CitiesInWorld citiesInWorld = new CitiesInWorld();
            citiesInWorld.runWithInputs(5);
            citiesInWorld.runWithInputs(0);

            // Populated Cities by Continent with and Without Limit
            CitiesInContinent citiesInContinent = new CitiesInContinent();
            citiesInContinent.runWithContinentandLimits(3,"Africa");

            // Populated Cities by Region with and Without Limit
            CitiesInRegion citiesInRegion = new CitiesInRegion();
            citiesInRegion.runWithRegionandLimits(3,"Caribbean");


            //Top Populated Countries By Region
            TopPopulationCountriesRegion topPopulationCountriesRegion = new TopPopulationCountriesRegion();
            topPopulationCountriesRegion.runWithLimit(10, "Caribbean");

            // Top Populated Cities by Region
            TopPopulatedCitiesByCountryReport topPopulatedCitiesByRegionReport = new TopPopulatedCitiesByCountryReport();
            topPopulatedCitiesByRegionReport.runWithLimitAndCountry(5, "Caribbean");

            // Top Populated Cities by Country
            TopPopulatedCitiesByCountryReport topPopulatedCitiesByCountryReport = new TopPopulatedCitiesByCountryReport();
            topPopulatedCitiesByCountryReport.runWithLimitAndCountry(5, "Cuba");

            /**
             * POPULATION REPORTS
             */

            // Population of people living in and out of cities in each continent
            PopulationInEachContinent populationInEachContinent = new PopulationInEachContinent();
            populationInEachContinent.run();

            // Population of people living in and out of cities in each region
            PopulationInEachRegion populationInEachRegion = new PopulationInEachRegion();
            populationInEachRegion.run();


            // Close Connection to Database
            ConnectionManager.getInstance().disconnect();
        }
    }
}
