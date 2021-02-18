package com.kayak.web;


import com.kayak.pomodel.POSearch;
import com.kayak.utilities.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search extends TestBase {
    POSearch poSearch;

    @BeforeMethod
    public void settingDependency(){
        poSearch = new POSearch(driverWeb);
        oSelUtil.goToKayakUrl();

    }

    @Test
    public  void searchFlightsInKayak() throws Exception{
        extentTest = extentReports.createTest("Search Flights in Kayak");
        poSearch.searchFlights2(15);
    }
}
