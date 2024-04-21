//UC 28
package com.napier.seprojectgroup6;

// Import DB, Reports and Navigation Folders for Testing
import com.napier.seprojectgroup6.db.*;
import com.napier.seprojectgroup6.reports.*;
import com.napier.seprojectgroup6.navigation.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {

    // Country Reports
    static TopPopulationCountriesRegion topPopulationCountriesRegion;

    // City Reports
    static CitiesByDistrictReport cityByDistrictReport;
    static TopPopulatedCitiesReport topPopCitiesReport;
    static CitiesInContinent citiesinContinentReport;
    static CitiesInCountry citiesInCountryReport;
    static CitiesInRegion citiesInRegionreport;
    static CitiesInWorld CitiesInWorldReport;
    static CitiesInDistrict citiesInDistrictReport;

    // Capital City Reports
    static PopulatedCapitalCitiesByContinent report;
    static PopulatedCapitalCitiesByRegion popCapCityRegionReport;
    static AllCapitalCitiesWorld allCapitalCitiesWorldReport;
    static PopulatedCapitalCitiesByDistrict popCapCityDistrictReport;
    static PopulatedCapitalCitiesinWorld populatedCapitalCitiesInWorldReport;

    // Population Reports
    static PopulationInEachRegion PopInEachRegionReport;
    static PopulationInEachCountry popInEachCountryReport;

    // Total Population Reports
    static TotalInContinent totalInContinentReport;
    static TotalInRegion totalInRegionReport;
    static TotalInCountry totalInCountryReport;
    static TotalInDistrict totalInDistrictReport;
    static WorldPopulation worldPopulation;

    // Language Report

    @BeforeAll
    static void init()
    {
        ConnectionManager.getInstance().connect("localhost:33060", 0);
        report = new PopulatedCapitalCitiesByContinent();
        popCapCityRegionReport = new PopulatedCapitalCitiesByRegion();
        cityByDistrictReport = new CitiesByDistrictReport();
        topPopCitiesReport = new TopPopulatedCitiesReport();
        PopInEachRegionReport = new PopulationInEachRegion();
        popInEachCountryReport = new PopulationInEachCountry();
        citiesinContinentReport = new CitiesInContinent();
        citiesInCountryReport =  new CitiesInCountry();
        citiesInRegionreport = new CitiesInRegion();
        CitiesInWorldReport = new CitiesInWorld();
        citiesInDistrictReport = new CitiesInDistrict();
        topPopulationCountriesRegion = new TopPopulationCountriesRegion();
        totalInContinentReport = new TotalInContinent();
        totalInRegionReport = new TotalInRegion();
        totalInCountryReport = new TotalInCountry();
        totalInDistrictReport = new TotalInDistrict();
        popCapCityDistrictReport = new PopulatedCapitalCitiesByDistrict();
        worldPopulation = new WorldPopulation();
        allCapitalCitiesWorldReport = new AllCapitalCitiesWorld();
        populatedCapitalCitiesInWorldReport = new PopulatedCapitalCitiesinWorld();


    }

    /**
     * COUNTRY REPORTS INTEGRATION TESTS
    **/


    /**
     * POPULATION REPORTS INTEGRATION TESTS
     */

    // Population in Each Country returns expected Results
    @Test
    void testPopInEachCountry(){
        Population population = new Population();

        popInEachCountryReport.run();
        assertEquals(popInEachCountryReport.populations.size(), 239);
    }

     // Population in each Region returns expected results
    @Test
    void testPopInEachRegion(){
        Population population = new Population();

        PopInEachRegionReport.run();
        assertEquals(PopInEachRegionReport.populations.size(), 25);
    }


    /**
     * CITY REPORTS INTEGRATION TESTS
     */

    // City by District if empty, returns no results
    @Test
    void testRunWithEmptyDistrict() {
        City city = new City();

        cityByDistrictReport.runWithDistrict("");
        assertEquals(cityByDistrictReport.cities.size(), 0);
    }

    // City by District returns expected results
    @Test
    void testRunWithDistrict() {
        City city = new City();

        cityByDistrictReport.runWithDistrict("Aichi");
        assertEquals(cityByDistrictReport.cities.size(), 15);
    }

    /** All Cities In Continent with Limit  */
    @Test
    void testRunCitiesInContinent()
    {
        City city = new City();
        citiesinContinentReport.runWithContinentandLimits(0,"Africa");
        assertEquals(citiesinContinentReport.cities.size(), 366);
    }

    /** All CITIES In Region with Limit */
    @Test
    void testRunCitiesInRegion()
    {
        City city = new City();
        citiesInRegionreport.runWithRegionandLimits(0,"Caribbean");
        assertEquals(citiesInRegionreport.cities.size(), 58);
    }

    /** All CITIES In Country with Limit */

    @Test
    void testRunCitiesInCountry() {
        City city = new City();
        citiesInCountryReport.runWithCountryandLimits(0,"united states");
        assertEquals(citiesInCountryReport.cities.size(),274);
    }

    /** All CITIES in the World */
    @Test
    void testCitiesInWorld() {
        City city = new City();
        CitiesInWorldReport.runWithInputs(5);
        assertEquals(CitiesInWorldReport.cities.size(),5);
    }

    @Test
    void testRunWithLimitAndCity()
    {
        City City = new City();
        CitiesInWorldReport.runWithInputs(5);
        assertEquals(CitiesInWorldReport.cities.size(),5);
    }


    /**
     * CAPITAL CITY REPORTS INTEGRATION TESTS
     */

    /**
     * Populated Capital Cities by District
     */
    @Test
    void testRunWithLimitandDistrict()
    {
        PopulatedCapitalCitiesByDistrict populatedCapitalCitiesByDistrict = new PopulatedCapitalCitiesByDistrict();
        popCapCityDistrictReport.runWithInputs(3,"Acre");
        assertEquals(popCapCityDistrictReport.capitalCities.size(), 1);

    }


    //UC17
    @Test
    void testAllCapitalCitiesWorld(){
        allCapitalCitiesWorldReport.run();
        assertEquals(allCapitalCitiesWorldReport.capitalCities.size(),4079);
    }

    //UC20
    @Test
    void testRunWithLimit() {
        populatedCapitalCitiesInWorldReport.runWithInputs(5);
        assertEquals(populatedCapitalCitiesInWorldReport.capitalCities.size(), 5);
    }

    // Capital City by Continent if empty returns no results
    @Test
    void testRunWithEmptyLimitAndContinent()
    {
        CapitalCity capitalCity = new CapitalCity();

        report.runWithInputs(null,"");
        assertEquals(report.capitalCities.size(),0);
    }

    @Test
    void testRunWithEmptyLimitWithContinent()
    {
        CapitalCity capitalCity = new CapitalCity();

        report.runWithInputs(null,"Africa");
        assertEquals(report.capitalCities.size(),0);
    }

    @Test
    void testRunWithContinent()
    {
        CapitalCity capitalCity = new CapitalCity();

        report.runWithInputs(0,"Africa");
        assertEquals(report.capitalCities.size(),366);
    }

    @Test
    void testRunWithIncorrectContinent()
    {
        CapitalCity capitalCity = new CapitalCity();

        report.runWithInputs(0,"Test");
        assertEquals(report.capitalCities.size(),0);
    }

    @Test
    void testRunWithLimitAndContinent()
    {
        CapitalCity capitalCity = new CapitalCity();

        report.runWithInputs(5,"Africa");
        assertEquals(report.capitalCities.size(),5);
    }


    /**
     * POPULATED CAPITAL CITIES BY REGION
     */
    @Test
    void testRunWithEmptyRegion()
    {
        CapitalCity capitalCity = new CapitalCity();

        popCapCityRegionReport.runWithInputs(5,"");
        assertEquals(popCapCityRegionReport.capitalCities.size(),0);
    }

    @Test
    void testRunWithEmptyLimitAndRegion()
    {
        CapitalCity capitalCity = new CapitalCity();

        popCapCityRegionReport.runWithInputs(null,"");
        assertEquals(popCapCityRegionReport.capitalCities.size(),0);
    }

    @Test
    void testRunWithEmptyLimitWithRegion()
    {
        CapitalCity capitalCity = new CapitalCity();

        popCapCityRegionReport.runWithInputs(null,"Caribbean");
        assertEquals(popCapCityRegionReport.capitalCities.size(),0);
    }

    @Test
    void testRunWithRegion()
    {
        CapitalCity capitalCity = new CapitalCity();

        popCapCityRegionReport.runWithInputs(5,"Caribbean");
        assertEquals(popCapCityRegionReport.capitalCities.size(),5);
    }

    @Test
    void testRunWithIncorrectRegion()
    {
        CapitalCity capitalCity = new CapitalCity();

        popCapCityRegionReport.runWithInputs(5,"Test");
        assertEquals(popCapCityRegionReport.capitalCities.size(),0);
    }

    /** All Cities In District with Limit  */
    @Test
    void testrunWithDistrictandLimits()
    {
        City city = new City();
        citiesInDistrictReport.runWithDistrictandLimits(0, "Zuid-Holland");
        assertEquals(citiesInDistrictReport.cities.size(), 6);
    }

    /**
     * Top N Population of countries in Region Report
     */
    @Test
    void testRunWithtopPopulationCountriesRegion()
    {
        Population population = new Population();
        topPopulationCountriesRegion.runWithLimit(10,"Caribbean");
        assertEquals(topPopulationCountriesRegion.countries.size(), 10);
    }

    /**
     * Population of the World
     */
    @Test
    void testWorldPopulation()
    {
        WorldPopulation worldPopulation = new WorldPopulation();
        worldPopulation.run();
        assertEquals(worldPopulation.WorldPopulation,6078749450L);
    }

    /**
     * Total Population of a Continent
     */
    @Test
    void testRunTotalInContinent()
    {
        TotalInContinent totalInContinent = new TotalInContinent();
        totalInContinentReport.runWithInputs("Europe");
        assertEquals(totalInContinentReport.TotalInContinent, 730074600L);
    }

    /**
     * Total Population of Region
     */
    @Test
    void testRunTotalInRegion()
    {
        TotalInRegion totalInRegion = new TotalInRegion();
        totalInRegionReport.runWithInputs("Caribbean");
        assertEquals(totalInRegionReport.TotalInRegion, 38140000);
    }


    /**
     * Total Population of Country
     */
    @Test
    void testRunTotalInCountry()
    {
        TotalInCountry totalInCountry = new TotalInCountry();
        totalInCountryReport.runWithInputs("Brazil");
        assertEquals(totalInCountryReport.TotalInCountry, 170115000);
    }

    /**
     * Total Population of District
     */
    @Test
    void testRunTotalInDistrict()
    {
        TotalInDistrict totalInDistrict = new TotalInDistrict();
        totalInDistrictReport.runWithInputs("Balkh");
        assertEquals(totalInDistrictReport.TotalInDistrict, 127800);
    }

}