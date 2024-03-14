package com.napier.seprojectgroup6;


import com.napier.seprojectgroup6.db.ConnectionManager;
import com.napier.seprojectgroup6.navigation.Navigator;
import com.napier.seprojectgroup6.reports.CitiesByDistrictReport;
public class App {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        // Connect to database and instantiate Navigator
        // if running locally i.e not on Github actions.

        System.out.println("Project Works!");
        ConnectionManager.getInstance().connect(() -> {
            System.out.println("GITHUB_ACTIONS " + System.getenv("GITHUB_ACTIONS"));
            boolean isRunningOnGithub = true;
            if(!isRunningOnGithub) {
                Navigator navigator = new Navigator();
            }
            else {

                CitiesByDistrictReport report = new CitiesByDistrictReport();
                report.runWithDistrict("Aichi");

                ConnectionManager.getInstance().disconnect();
            }
        });

        // Disconnect from database
        //a.disconnect();
    }
}
