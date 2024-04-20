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

    // Country Reports UC01 - UC06
    static PopulationInEachCountry popInEachCountryReport;
    static TopPopulationCountriesRegion topPopulationCountriesRegion;

    // City Reports UC07 - UC16
    static CitiesByDistrictReport cityByDistrictReport;
    static TopPopulatedCitiesReport topPopCitiesReport;
    static CitiesInContinent citiesinContinentReport;
    static CitiesInCountry citiesInCountryReport;
    static CitiesInRegion citiesInRegionreport;
    static CitiesInWorld CitiesInWorldReport;
    static CitiesInDistrict citiesInDistrictReport;

    // Capital City Reports UC17 - UC22
    static PopulatedCapitalCitiesByContinent report;
    static PopulatedCapitalCitiesByRegion popCapCityRegionReport;
    static AllCapitalCitiesWorld allCapitalCitiesWorldReport;
    static PopulatedCapitalCitiesinWorld populatedCapitalCitiesInWorldReport;
    static PopulatedCapitalCitiesByDistrict popCapCityDistrictReport;

    // Population Reports UC23 - 31
    static PopulationInEachRegion PopInEachRegionReport;
    static WorldPopulation worldPopulation;
    static TotalInContinent totalInContinentReport;
    static TotalInRegion totalInRegionReport;
    static TotalInCountry totalInCountryReport;
    static TotalInDistrict totalInDistrictReport;

    // Language Report UC32



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
     * Population in Each Region Report
     */

    @Test
    void testPopInEachCountry(){
        Population population = new Population();

        popInEachCountryReport.run();
        assertEquals(popInEachCountryReport.populations.size(), 239);
    }

    /**
     * Population in Each Region Report
     */

    @Test
    void testPopInEachRegion(){
        Population population = new Population();

        PopInEachRegionReport.run();
        assertEquals(PopInEachRegionReport.populations.size(), 25);
    }
    /**
     * TOP POPULATED CITIES BY DISTRICT
     */
    @Test
    void testRunWithEmptyDistrct() {
        City city = new City();

        topPopCitiesReport.runWithLimit(0);
        assertEquals(topPopCitiesReport.cities.size(), 0);
    }

    @Test
    void testRunWithDistrct() {
        City city = new City();

        topPopCitiesReport.runWithLimit(5);
        assertEquals(topPopCitiesReport.cities.size(), 5);
    }

    /**
     * CITIES BY DISTRICT

    @Test
    void testRunWithEmptyDistrict() {
        City city = new City();

        cityByDistrictReport.runWithDistrict("");
        assertEquals(cityByDistrictReport.cities.size(), 0);
    }

    @Test
    void testRunWithDistrict() {
        City city = new City();

        cityByDistrictReport.runWithDistrict("Aichi");
        assertEquals(cityByDistrictReport.cities.size(), 15);
    }
     */


    /**
     * POPULATED CAPITAL CITIES BY CONTINENT
     */
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

    /**
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
/**
    @Test
    void testTopPopulatedCitiesByContinentReportLimitWorks() {
        topPopulatedCitiesByContinentReport.runWithLimitAndContinent(10, "Africa");
        assertEquals(topPopulatedCitiesByContinentReport.cities.size(), 10);
    }
    @Test
    void testTopPopulatedCityIsCorrect() {
        topPopulatedCitiesByContinentReport.runWithLimitAndContinent(10, "Africa");
        City topCity = topPopulatedCitiesByContinentReport.cities.get(0);
        assertEquals(topCity.name, "Cairo");
    }
*/

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



