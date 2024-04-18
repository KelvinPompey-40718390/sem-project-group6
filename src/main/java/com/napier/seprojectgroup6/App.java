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
        // if running locally i.e not on Github actions.

        System.out.println("Project Works!");

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

            CitiesByDistrictReport citiesByDistrictReport = new CitiesByDistrictReport();
            citiesByDistrictReport.runWithDistrict("Aichi");
/**
            TopPopulatedCitiesReport topPopulatedCitiesReport = new TopPopulatedCitiesReport();
            topPopulatedCitiesReport.runWithLimit(5);
*/
            /**
             *  CAPITAL CITY REPORTS
             */
            // Populated Capital Cities by Continent with Limit
            PopulatedCapitalCitiesByContinent populatedCapitalCitiesByContinent = new PopulatedCapitalCitiesByContinent();
            populatedCapitalCitiesByContinent.runWithInputs(5,"Africa");

            // ALL Populated Capital Cities by Continent
            populatedCapitalCitiesByContinent.runWithInputs(0,"Africa");

            // Populated Capital Cities by Region with Limit
            PopulatedCapitalCitiesByRegion populatedCapitalCitiesByRegion = new PopulatedCapitalCitiesByRegion();
            populatedCapitalCitiesByRegion.runWithInputs(5,"Caribbean");

            // ALL Populated Capital Cities by Region
            populatedCapitalCitiesByRegion.runWithInputs(0,"Caribbean");

            /* Removed by A Jardine due to Missing Class Reference
            // All Capital Cities in the World
            AllCapitalCitiesWorld allCapitalCitiesWorld = new AllCapitalCitiesWorld();
            allCapitalCitiesWorld.run();
            */

            // Populated Cities by Continent
            CitiesInContinent citiesInContinent = new CitiesInContinent();
            citiesInContinent.runWitContinent("Africa");

            // Populated Cities by Country
            CitiesInCountry citiesInCountry = new CitiesInCountry();
            citiesInCountry.runWithCountry("North America");

            // Populated Cities by Region
            CitiesInRegion citiesInRegion = new CitiesInRegion();
            citiesInRegion.runWithRegion("Caribbean");


            /**
             *  Populated Cities In World
             */

            //Cities in the World
            CitiesInWorld citiesInWorld = new CitiesInWorld();
            citiesInWorld.run();

            // Cities in the World with Limit
            citiesInWorld.runWithInputs(5,"Bridgetown");




            //Top Populated Countries By Region
            TopPopulationCountriesRegion topPopulationCountriesRegion = new TopPopulationCountriesRegion();
            topPopulationCountriesRegion.runWithLimit(10, "Caribbean");

            // Top Populated Cities by Region
            TopPopulatedCitiesByCountryReport topPopulatedCitiesByRegionReport = new TopPopulatedCitiesByCountryReport();
            topPopulatedCitiesByRegionReport.runWithLimitAndCountry(5, "Caribean");

            // Top Populated Cities by Country
            TopPopulatedCitiesByCountryReport topPopulatedCitiesByCountryReport = new TopPopulatedCitiesByCountryReport();
            topPopulatedCitiesByCountryReport.runWithLimitAndCountry(5, "Cuba");


            ConnectionManager.getInstance().disconnect();
        }
    }
}
