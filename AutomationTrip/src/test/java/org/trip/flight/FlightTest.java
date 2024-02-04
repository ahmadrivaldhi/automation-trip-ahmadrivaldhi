package org.trip.flight;

import automation.pages.flight.FlightPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.trip.base.BaseTest;

public class FlightTest extends BaseTest {

    FlightPage flightPage;

    @BeforeMethod
    public void setupMethod(){
        if (webDriver == null) {
            initWebDriver();
            openBrowser();
        }
        flightPage = new FlightPage(webDriver);
    }

    @AfterMethod
    public void tearDown(){
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    @Test(priority = 1)
    public void searchFlightsBangkokSingapore() {
        flightPage.setFromCity("Bangkok");
        flightPage.setToCity("Singapore");
        flightPage.setDateStart("15");
        flightPage.setDateEnd("16");
        flightPage.clickSearch();
        flightPage.verifyResultSearch();
    }

    @Test(priority = 2)
    public void searchFlightWithFilterRefundable(){
        flightPage.setFromCity("Jakarta");
        flightPage.setToCity("London");
        flightPage.setDateStart("15");
        flightPage.setDateEnd("20");
        flightPage.clickSearch();
        flightPage.verifyResultSearch();
        flightPage.showMorePopularFilter();
        flightPage.selectCheckboxFilter("Refundable");
        flightPage.verifyFilterApplied("Refundable");
    }

    @Test(priority = 3)
    public void searchFlightWithMultiplePopularFilter(){
        flightPage.setFromCity("Jakarta");
        flightPage.setToCity("London");
        flightPage.setDateStart("15");
        flightPage.setDateEnd("20");
        flightPage.clickSearch();
        flightPage.verifyResultSearch();
        flightPage.showMorePopularFilter();
        flightPage.selectCheckboxFilter("AfterNoon");
        flightPage.verifyFilterApplied("AfterNoon");
        flightPage.selectCheckboxFilter("Non Stop");
        flightPage.verifyFilterApplied("Non Stop");
        flightPage.selectCheckboxFilter("Morning Departures");
        flightPage.verifyFilterApplied("Morning Departures");
    }
}
