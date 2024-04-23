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
    public static TopPopulatedCitiesReport topPopCitiesReport;
    public static TopPopulatedCountries topPopulatedCountriesReport;
    public static PopulationInEachRegion popInEachRegionReport;
    public static PopulationInEachCountry popInEachCountryReport;
    public static PopulationInEachContinent popInEachContinentReport;
    // Country Reports
    public static TopPopulationCountriesRegion topPopulationCountriesRegion;
    public static CountriesInWorld countriesInWorld;

    public static CitiesInContinent citiesInContinentReport;
    public static CitiesInCountry citiesInCountryReport;
    public static CitiesInRegion citiesInRegionReport;
    public static CitiesInWorld citiesInWorldReport;
    public static CitiesInDistrict citiesInDistrictReport;
    public static PopulationOfACity populationOfACity;
    public static CountriesInWorld countriesInWorldReport;
    public static TotalInContinent totalInContinentReport;
    public static TotalInRegion totalInRegionReport;
    public static TotalInCountry totalInCountryReport;
    public static TotalInDistrict totalInDistrictReport;
    public static WorldPopulation worldPopulation;
    public static PopulatedCapitalCitiesinWorld populatedCapitalCitiesInWorldReport;

    public static NumberOfPeopleSpeakingACertainLanguage languageReport;

    private IntegrationTest() {}
    @BeforeAll
    static void init()
    {
        ConnectionManager.getInstance().connect("localhost:33060", 0);
        report = new PopulatedCapitalCitiesByContinent();
        popCapCityRegionReport = new PopulatedCapitalCitiesByRegion();
        topPopCitiesReport = new TopPopulatedCitiesReport();
        topPopulatedCountriesReport = new TopPopulatedCountries();
        popInEachRegionReport = new PopulationInEachRegion();
        popInEachCountryReport = new PopulationInEachCountry();
        popInEachContinentReport = new PopulationInEachContinent();
        populationOfACity = new PopulationOfACity();
        citiesInContinentReport = new CitiesInContinent();
        citiesInCountryReport =  new CitiesInCountry();
        citiesInRegionReport = new CitiesInRegion();
        citiesInWorldReport = new CitiesInWorld();
        citiesInDistrictReport = new CitiesInDistrict();
        topPopulationCountriesRegion = new TopPopulationCountriesRegion();
        totalInContinentReport = new TotalInContinent();
        totalInRegionReport = new TotalInRegion();
        totalInCountryReport = new TotalInCountry();
        totalInDistrictReport = new TotalInDistrict();
        worldPopulation = new WorldPopulation();
        populatedCapitalCitiesInWorldReport = new PopulatedCapitalCitiesinWorld();
        countriesInWorldReport = new CountriesInWorld();
        languageReport = new NumberOfPeopleSpeakingACertainLanguage();
        countriesInWorld = new CountriesInWorld();
    }


    /**
     * COUNTRY REPORTS INTEGRATION TESTS
    **/
    @Test
    void setCountriesInWorld(){
        Population population = new Population();

        countriesInWorld.run();
        assertEquals(countriesInWorld.countries.size(), 232);
    }

    /**
     * POPULATION REPORTS INTEGRATION TESTS
     */

    // Population in Each Country returns expected Results
    @Test
    void testPopInEachCountry(){

        popInEachCountryReport.run();
        assertEquals(popInEachCountryReport.populations.size(), 239);
    }

     // Population in each Region returns expected results
    @Test
    void testPopInEachRegion(){

        popInEachRegionReport.run();
        assertEquals(popInEachRegionReport.populations.size(), 25);
    }


    @Test
    void testPopInEachContinentReturnsCorrectNumberOfContinents(){
        popInEachContinentReport.run();
        assertEquals(popInEachContinentReport.populations.size(), 7);
    }

    @Test
    void testPopInEachContinentReturnsCorrectValues(){
        popInEachContinentReport.run();
        Population northAmericanPopulation = popInEachContinentReport.populations.get(0);
        Population asiaPopulation = popInEachContinentReport.populations.get(1);
        Population africaPopulation = popInEachContinentReport.populations.get(2);
        Population europePopulation = popInEachContinentReport.populations.get(3);
        Population southAmericaPopulation = popInEachContinentReport.populations.get(4);
        Population oceaniaPopulation = popInEachContinentReport.populations.get(5);
        Population antarcticaPopulation = popInEachContinentReport.populations.get(6);

        assert (northAmericanPopulation.name.equals("North America") && northAmericanPopulation.pctLivingInCities.equals("3.96%") && northAmericanPopulation.pctNotLivingInCities.equals("96.04%")  );
        assert (asiaPopulation.name.equals("Asia") && asiaPopulation.pctLivingInCities.equals("2.73%") && asiaPopulation.pctNotLivingInCities.equals("97.27%"));
        assert (africaPopulation.name.equals("Africa") && africaPopulation.pctLivingInCities.equals("6.08%") && africaPopulation.pctNotLivingInCities.equals("93.92%")) ;
        assert (europePopulation.name.equals("Europe") && europePopulation.pctLivingInCities.equals("7.11%") && europePopulation.pctNotLivingInCities.equals("92.89%")) ;
        assert (southAmericaPopulation.name.equals("South America") && southAmericaPopulation.pctLivingInCities.equals("8.36%") && southAmericaPopulation.pctNotLivingInCities.equals("91.64%")) ;
        assert (oceaniaPopulation.name.equals("Oceania") && oceaniaPopulation.pctLivingInCities.equals("3.76%") && oceaniaPopulation.pctNotLivingInCities.equals("96.24%")) ;
        assert (antarcticaPopulation.name.equals("Antarctica") && antarcticaPopulation.pctLivingInCities.equals("0.00%") && antarcticaPopulation.pctNotLivingInCities.equals("0.00%")) ;
    }


    @Test
    void testPopulatedRegionsReturnsAllCountries() {
        topPopulatedCountriesReport.runWithLimit(0, "Caribbean");
        assertEquals(topPopulatedCountriesReport.countries.size(), 24);
    }

    @Test
    void testPopulatedRegionsReturnsLimitedCountries() {
        topPopulatedCountriesReport.runWithLimit(5, "Caribbean");
        assertEquals(topPopulatedCountriesReport.countries.size(), 5);
    }

    @Test
    void testPopulatedRegionsReturnsCountriesInDescendingOrder() {
        topPopulatedCountriesReport.runWithLimit(5, "Caribbean");

        Country firstCountry = topPopulatedCountriesReport.countries.get(0);
        boolean result = true;

        for(Country country : topPopulatedCountriesReport.countries) {
            if(country.population > firstCountry.population) {
                result = false;
            }
        }
        assertTrue(result);
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

    /*
     * CITY REPORTS INTEGRATION TESTS
     */

    /** All CITIES in the World */
    //cities in the world with limits//
    @Test
    void testAllCitiesInWorld() {
        citiesInWorldReport.runWithInputs(0);
        assertEquals(citiesInWorldReport.cities.size(),4079);
    }
    //With limit and city
    @Test
    void testCitiesInWorldWithLimitAndCity()
    {
        citiesInWorldReport.runWithInputs(5);
        assertEquals(citiesInWorldReport.cities.size(),5);

    }


    /** All Cities In Continent with Limit  */
    // continent no limits
    @Test
    void testRunCitiesInContinentNoLimits()
    {
        citiesInContinentReport.runWithContinentAndLimits(0,"Africa");
        assertEquals(citiesInContinentReport.cities.size(), 366);
    }
    // with limits and continent//
    @Test
    void testRunCitiesInContinentWithLimits()
    {
        citiesInContinentReport.runWithContinentAndLimits(10,"Africa");
        assertEquals(citiesInContinentReport.cities.size(), 10);
    }

    //run with invalid continent//
    @Test
    void testRunCitiesWithInvalidContinent()
    {
        citiesInContinentReport.runWithContinentAndLimits(0,"Afr'ica");
        assertEquals(citiesInContinentReport.cities.size(), 0);
    }

    /** All CITIES In Region integration tests */
    //region with limits//
    @Test
    void testRunCitiesInRegion()
    {
        citiesInRegionReport.runWithRegionAndLimits(5,"Caribbean");
        assertEquals(citiesInRegionReport.cities.size(), 5);
    }
    //region with no limits//
    @Test
    void testRunAllCitiesInRegionWithNoLimits()
    {
        citiesInRegionReport.runWithRegionAndLimits(0,"Caribbean");
        assertEquals(citiesInRegionReport.cities.size(), 58);
    }
    // with invalid region//
    @Test
    void testRunCitiesInvalidRegion()
    {
        citiesInRegionReport.runWithRegionAndLimits(0,"Caribb'ean");
        assertEquals(citiesInRegionReport.cities.size(), 0);
    }

    /** All CITIES In Country with Limit */
    //country no limits//

    @Test
    void testRunCitiesInCountry() {
        citiesInCountryReport.runWithCountryAndLimits(0,"united states");
        assertEquals(citiesInCountryReport.cities.size(),274);
    }
    //Country with limits//
    @Test
    void testRunCitiesInCountryWithLimits() {
        citiesInCountryReport.runWithCountryAndLimits(5,"united states");
        assertEquals(citiesInCountryReport.cities.size(),5);
    }

    //invalid country//
    @Test
    void testRunCitiesInCountryWithError() {
        citiesInCountryReport.runWithCountryAndLimits(0,"unit'ed states");
        assertEquals(citiesInCountryReport.cities.size(),0);
    }


    @Test
    void testCountriesInWorldReturnsAllCountries() {
        countriesInWorldReport.run();
        assertEquals(countriesInWorldReport.countries.size(), 232);
    }

    /** All Cities In District  Integration tests */
    //district and no limits
    @Test
    void testRunWithDistrictNoLimits()
    {
        citiesInDistrictReport.runWithDistrictandLimits(0, "Zuid-Holland");
        assertEquals(citiesInDistrictReport.cities.size(), 6);
    }

    //district and limits
    @Test
    void testRunWithDistrictAndLimits()
    {
        citiesInDistrictReport.runWithDistrictandLimits(3, "Zuid-Holland");
        assertEquals(citiesInDistrictReport.cities.size(), 3);
    }

    //no district no limits//
    @Test
    void testRunWithDistrictNoLimitsEmptyDistrict()
    {
        citiesInDistrictReport.runWithDistrictandLimits(0, " ");
        assertEquals(citiesInDistrictReport.cities.size(), 0);
    }

    // invalid district to trigger error in query//
    @Test
    void testRunWithInvalidDistrict()
    {
        citiesInDistrictReport.runWithDistrictandLimits(0, "Noor'd-Holland");
        assertEquals(citiesInDistrictReport.cities.size(), 0);
    }

    @Test
    void testPopulationOfACity() {
        populationOfACity.runWithCity("Paris");
        assertEquals(populationOfACity.city.population, 2125246L);
    }
    /*
     * END OF CITY REPORTS INTEGRATION TESTS
     */

    /*
     * CAPITAL CITY REPORTS INTEGRATION TESTS
     */

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
    void testRunAllCitiesInRegion()
    {
        citiesInRegionReport.runWithRegionAndLimits(0,"Caribbean");
        assertEquals(citiesInRegionReport.cities.size(), 58);
    }

    /**
     * All CITIES In Country
     */
    @Test
    void testRunWithCountry() {

        citiesInCountryReport.runWithCountryAndLimits (0, "united states");
        assertEquals(citiesInCountryReport.cities.size(), 274);
    }



    /** All CITIES in the World */
    @Test
    void testTop5CitiesInWorld() {

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

    //UC20
    @Test
    void testPopulatedCitiesInWorldWithLimit() {
        populatedCapitalCitiesInWorldReport.runWithInputs(5);
        assertEquals(populatedCapitalCitiesInWorldReport.capitalCities.size(), 5);
    }

    @Test
    void testLanguageReportsReturnsCorrectNumberOfLanguages() {
     languageReport.run();
     assertEquals(languageReport.populations.size(), 5);
    }

    @Test
    void testLanguageReportReturnsCorrectValues() {
        languageReport.run();

        Population arabicPopulation = languageReport.populations.get(0);
        Population englishPopulation = languageReport.populations.get(1);
        Population spanishPopulation = languageReport.populations.get(2);
        Population hindiPopulation = languageReport.populations.get(3);
        Population chinesePopulation = languageReport.populations.get(4);

        assert(arabicPopulation.name.equals("Arabic") && arabicPopulation.totalPopulation == 233839238L );
        assert(englishPopulation.name.equals("English") && englishPopulation.totalPopulation == 347077867L);
        assert(spanishPopulation.name.equals("Spanish") && spanishPopulation.totalPopulation == 355029462L);
        assert(hindiPopulation.name.equals("Hindi") && hindiPopulation.totalPopulation == 405633070L);
        assert(chinesePopulation.name.equals("Chinese") && chinesePopulation.totalPopulation == 1191843539L);
    }
}



