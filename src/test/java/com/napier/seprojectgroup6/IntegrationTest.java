//UC 28
package com.napier.seprojectgroup6;

// Import DB, Reports and Navigation Folders for Testing
import com.napier.seprojectgroup6.db.*;
import com.napier.seprojectgroup6.reports.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class IntegrationTest {

    public static PopulatedCapitalCitiesByContinent report;
    public static PopulatedCapitalCitiesByRegion popCapCityRegionReport;
    public static CitiesByDistrictReport cityByDistrictReport;
    public static TopPopulatedCitiesReport topPopCitiesReport;
    public static PopulationInEachRegion popInEachRegionReport;
    public static PopulationInEachCountry popInEachCountryReport;

    public static CitiesInContinent citiesInContinentReport;
    public static CitiesInCountry citiesInCountryReport;
    public static CitiesInRegion citiesInRegionReport;
    public static CitiesInWorld citiesInWorldReport;
    public static TopPopulationCountriesRegion topPopulationCountriesRegion;
    public static TotalInContinent totalInContinentReport;
    public static TotalInRegion totalInRegionReport;
    public static TotalInCountry totalInCountryReport;
    public static TotalInDistrict totalInDistrictReport;
    public static PopulatedCapitalCitiesByDistrict popCapCityDistrictReport;
    public static WorldPopulation worldPopulation;

    public static TopPopulatedCitiesByContinentReport topPopulatedCitiesByContinentReport;
    public static TopPopulatedCitiesByRegionReport topPopulatedCitiesByRegionReport;
    public static TopPopulatedCitiesByCountryReport topPopulatedCitiesByCountryReport;
    public static AllCapitalCitiesWorld allCapitalCitiesWorldReport;
    public static PopulatedCapitalCitiesinWorld populatedCapitalCitiesInWorldReport;

    private IntegrationTest() {}
    @BeforeAll
    static void init()
    {
        ConnectionManager.getInstance().connect("localhost:33060", 0);
        report = new PopulatedCapitalCitiesByContinent();
        popCapCityRegionReport = new PopulatedCapitalCitiesByRegion();
        cityByDistrictReport = new CitiesByDistrictReport();
        topPopCitiesReport = new TopPopulatedCitiesReport();
        popInEachRegionReport = new PopulationInEachRegion();
        popInEachCountryReport = new PopulationInEachCountry();
        citiesInContinentReport = new CitiesInContinent();
        citiesInCountryReport =  new CitiesInCountry();
        citiesInRegionReport = new CitiesInRegion();
        citiesInWorldReport = new CitiesInWorld();
        topPopulationCountriesRegion = new TopPopulationCountriesRegion();
        totalInContinentReport = new TotalInContinent();
        totalInRegionReport = new TotalInRegion();
        totalInCountryReport = new TotalInCountry();
        totalInDistrictReport = new TotalInDistrict();
        popCapCityDistrictReport = new PopulatedCapitalCitiesByDistrict();
        worldPopulation = new WorldPopulation();
        topPopulatedCitiesByContinentReport = new TopPopulatedCitiesByContinentReport();
        topPopulatedCitiesByRegionReport = new TopPopulatedCitiesByRegionReport();
        topPopulatedCitiesByCountryReport = new TopPopulatedCitiesByCountryReport();
        allCapitalCitiesWorldReport = new AllCapitalCitiesWorld();
        populatedCapitalCitiesInWorldReport = new PopulatedCapitalCitiesinWorld();


    }

    /**
     * Population in Each Region Report
     */

    @Test
    void testPopInEachCountry(){

        popInEachCountryReport.run();
        assertEquals(popInEachCountryReport.populations.size(), 239);
    }

    /**
     * Population in Each Region Report
     */

    @Test
    void testPopInEachRegion(){

        popInEachRegionReport.run();
        assertEquals(popInEachRegionReport.populations.size(), 25);
    }
    /**
     * TOP POPULATED CITIES BY DISTRICT
     */
    @Test
    void testRunPopCitiesReportWithZeroLimit() {

        topPopCitiesReport.runWithLimit(0);
        assertEquals(topPopCitiesReport.cities.size(), 0);
    }

    @Test
    void testRunPopCitiesWithDistrict() {

        topPopCitiesReport.runWithLimit(5);
        assertEquals(topPopCitiesReport.cities.size(), 5);
    }

    /**
     * CITIES BY DISTRICT
     */
    @Test
    void testRunCitiesByDistrictReportWithEmptyDistrict() {

        cityByDistrictReport.runWithDistrict("");
        assertEquals(cityByDistrictReport.cities.size(), 0);
    }

    @Test
    void testRunWithDistrict() {

        cityByDistrictReport.runWithDistrict("Aichi");
        assertEquals(cityByDistrictReport.cities.size(), 15);
    }

    /**
     * POPULATED CAPITAL CITIES BY CONTINENT
     */
    @Test
    void testRunWithEmptyLimitAndContinent()
    {
        report.runWithInputs(null,"");
        assertEquals(report.capitalCities.size(),0);
    }

    @Test
    void testRunWithEmptyLimitWithContinent()
    {
        report.runWithInputs(null,"Africa");
        assertEquals(report.capitalCities.size(),0);
    }

    @Test
    void testRunWithContinent()
    {

        report.runWithInputs(0,"Africa");
        assertEquals(report.capitalCities.size(),366);
    }

    @Test
    void testRunWithIncorrectContinent()
    {
        report.runWithInputs(0,"Test");
        assertEquals(report.capitalCities.size(),0);
    }

    @Test
    void testRunWithLimitAndContinent()
    {

        report.runWithInputs(5,"Africa");
        assertEquals(report.capitalCities.size(),5);
    }


    /**
     * POPULATED CAPITAL CITIES BY REGION
     */
    @Test
    void testRunWithEmptyRegion()
    {
        popCapCityRegionReport.runWithInputs(5,"");
        assertEquals(popCapCityRegionReport.capitalCities.size(),0);
    }

    @Test
    void testRunWithEmptyLimitAndRegion()
    {

        popCapCityRegionReport.runWithInputs(null,"");
        assertEquals(popCapCityRegionReport.capitalCities.size(),0);
    }

    @Test
    void testRunWithEmptyLimitWithRegion()
    {
        popCapCityRegionReport.runWithInputs(null,"Caribbean");
        assertEquals(popCapCityRegionReport.capitalCities.size(),0);
    }

    @Test
    void testRunWithRegion()
    {
        popCapCityRegionReport.runWithInputs(5,"Caribbean");
        assertEquals(popCapCityRegionReport.capitalCities.size(),5);
    }

    @Test
    void testRunWithIncorrectRegion()
    {
        popCapCityRegionReport.runWithInputs(5,"Test");
        assertEquals(popCapCityRegionReport.capitalCities.size(),0);
    }

    /** All Cities In Continent with Limit  */
    @Test
    void testRunCitiesInContinent()
    {
        citiesInContinentReport.runWithContinentAndLimits(0,"Africa");
        assertEquals(citiesInContinentReport.cities.size(), 366);
    }
    /** All CITIES In Region with Limit */
    @Test
    void testRunCitiesInRegion()
    {
        citiesInRegionReport.runWithRegionAndLimits(0,"Caribbean");
        assertEquals(citiesInRegionReport.cities.size(), 58);
    }

    /**
     * All CITIES In Country
     */
    @Test
    void testRunWithCountry() {

        citiesInCountryReport.runWithCountry("united states");
        assertEquals(citiesInCountryReport.cities.size(), 274);
    }



    /** All CITIES in the World */
    @Test
    void testCitiesInWorld() {

        citiesInWorldReport.runWithInputs(5);
        assertEquals(citiesInWorldReport.cities.size(),5);
    }

    @Test
    void testRunWithLimitAndCity()
    {
        citiesInWorldReport.runWithInputs(5);
        assertEquals(citiesInWorldReport.cities.size(),5);
    }




    /**
     * Top N Population of countries in Region Report
     */
    @Test
    void testRunWithtopPopulationCountriesRegion()
    {
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
        assertEquals(worldPopulation.total,6078749450L);
    }

    /**
     * Total Population of a Continent
     */
    @Test
    void testRunTotalInContinent()
    {
        totalInContinentReport.runWithInputs("Europe");
        assertEquals(totalInContinentReport.total, 730074600L);
    }

    /**
     * Total Population of Region
     */
    @Test
    void testRunTotalInRegion()
    {
        totalInRegionReport.runWithInputs("Caribbean");
        assertEquals(totalInRegionReport.total, 38140000);
    }


    /**
     * Total Population of Country
     */
    @Test
    void testRunTotalInCountry()
    {
        totalInCountryReport.runWithInputs("Brazil");
        assertEquals(totalInCountryReport.total, 170115000);
    }


    /**
     * Total Population of District
     */
    @Test
    void testRunTotalInDistrict()
    {
        totalInDistrictReport.runWithInputs("Balkh");
        assertEquals(totalInDistrictReport.total, 127800);
    }

    /**
     * Populated Capital Cities by District
     */
    @Test
    void testRunWithLimitandDistrict()
    {
        popCapCityDistrictReport.runWithInputs(3,"Acre");
        assertEquals(popCapCityDistrictReport.capitalCities.size(), 1);

    }

    @Test
    void testTopPopulatedCitiesByRegionReturnsCorrectLimit() {

        topPopulatedCitiesByRegionReport.runWithLimitAndRegion(4, "Caribbean");
        assertEquals(topPopulatedCitiesByRegionReport.cities.size(), 4);

    }

    @Test
    void testTopPopulatedCitiesByCountryReturnsCorrectLimit() {

        topPopulatedCitiesByCountryReport.runWithLimitAndCountry(4, "Cuba");
        assertEquals(topPopulatedCitiesByCountryReport.cities.size(), 4);

    }

    @Test
    void testTopPopulatedCityIsCorrect() {
        topPopulatedCitiesByContinentReport.runWithLimitAndContinent(10, "Africa");
        City topCity = topPopulatedCitiesByContinentReport.cities.get(0);
        assertEquals(topCity.name, "Cairo");
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

}



