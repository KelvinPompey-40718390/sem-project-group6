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

            TopPopulatedCitiesReport topPopulatedCitiesReport = new TopPopulatedCitiesReport();
            topPopulatedCitiesReport.runWithLimit(5);

            // Populated Capital Cities by Continent
            PopulatedCapitalCitiesByContinent populatedCapitalCitiesByContinent = new PopulatedCapitalCitiesByContinent();
            populatedCapitalCitiesByContinent.runWithInputs(5,"Africa");

            // Populated Cities by Country
            CitiesInCountry citiesInCountry = new CitiesInCountry();
            citiesInCountry.runWithCountry("North America");

            // Populated Cities by Region
            CitiesInRegion citiesInRegion = new CitiesInRegion();
            citiesInRegion.runWithRegion("Caribbean");

            //All Cities in the World
            CitiesInWorld citiesInWorld = new CitiesInWorld();
            citiesInWorld.runWithCities();

            //Top Populated Countries By Region
            TopPopulationCountriesRegion topPopulationCountriesRegion = new TopPopulationCountriesRegion();
            topPopulationCountriesRegion.runWithLimit(10, "Caribbean");



            ConnectionManager.getInstance().disconnect();
        }
    }
}
