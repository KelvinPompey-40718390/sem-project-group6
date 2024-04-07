package com.napier.seprojectgroup6;

// Import DB, Reports and Navigation Folders for Testing
import com.napier.seprojectgroup6.db.*;
import com.napier.seprojectgroup6.reports.*;
import com.napier.seprojectgroup6.navigation.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {

    static PopulatedCapitalCitiesByContinent report;
    static PopulatedCapitalCitiesByRegion popCapCityRegionReport;
    static CitiesByDistrictReport cityByDistrictReport;
    static TopPopulatedCitiesReport topPopCitiesReport;
    static PopulationInEachRegion PopInEachRegionReport;
    static PopulationInEachCountry popInEachCountryReport;
    static CitiesInRegion citiesInRegionreport;
    static CitiesInWorld CitiesInWorldReport;
    static TopPopulationCountriesRegion topPopulationCountriesRegion;


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
        citiesInRegionreport = new CitiesInRegion();
        CitiesInWorldReport = new CitiesInWorld();
        topPopulationCountriesRegion = new TopPopulationCountriesRegion();


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
     */
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

    /**
     * All CITIES In Region
     */
    @Test
    void testRunCitiesInRegion() {
        City city = new City();

        citiesInRegionreport.runWithRegion("Caribbean");
        assertEquals(citiesInRegionreport.cities.size(), 0);
    }




     * All CITIES in the world
     */
    @Test
    void testCitiesInWorld() {
        City city = new City();

        CitiesInWorldReport.run();
        assertEquals(CitiesInWorldReport.cities.size(), 4079);
    }


     * Top N Population of countries in Region Report
     */
    @Test
    void testRunWithtopPopulationCountriesRegion() {
        Population population = new Population();

        topPopulationCountriesRegion.runWithLimit(10,"Caribbean");
        assertEquals(topPopulationCountriesRegion.countries.size(), 10);

    }


}



