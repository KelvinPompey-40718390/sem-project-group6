package com.napier.seprojectgroup6;


import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.navigation.Navigator;
import com.napier.seprojectgroup6.reports.CitiesByDistrictReport;
import com.napier.seprojectgroup6.reports.TopPopulatedCitiesByContinentReport;
import com.napier.seprojectgroup6.reports.TopPopulatedCitiesReport;

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
            ConnectionManager.getInstance().connect("localhost:33060", 30000);
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

            TopPopulatedCitiesByContinentReport topPopulatedCitiesByContinentReport = new TopPopulatedCitiesByContinentReport();
            topPopulatedCitiesByContinentReport.runWithLimitAndContinent(10, "Africa");

            ConnectionManager.getInstance().disconnect();
        }
    }
}
