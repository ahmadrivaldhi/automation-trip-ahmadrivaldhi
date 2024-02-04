package automation.pages.flight;

import automation.pages.base.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FlightPage extends BasePage {

    public FlightPage(WebDriver webDriver) {
        super(webDriver);
    }

    static class dataJourney {
        String fromCity;
        String toCity;
    }

    private static final dataJourney dataStored = new dataJourney();

    @FindBy(id = "fromCity")
    private WebElement fieldFromCity;

    @FindBy(id = "toCity")
    private WebElement fieldToCity;

    @FindBy(css = "[placeholder='From']")
    private WebElement inputFromCity;

    @FindBy(css = "[placeholder='To']")
    private WebElement inputToCity;

    @FindBy(css = "[for='departure']")
    private WebElement fieldDeparture;

    @FindBy(xpath = "//span[.='Return']")
    private WebElement fieldReturn;

    @FindBy(xpath = "(//*[contains(@id,'react-autowhatever-1-section')])[1]")
    private WebElement resultDestination;

    @FindBy(xpath = "//*[@id='listingFilterCheckbox']")
    private WebElement checkboxFilterSearch;

    @FindBy(xpath = "//*[@class='checkboxTitle']")
    private WebElement checkboxTitle;

    @FindBy(xpath = "(//*[.='OKAY, GOT IT!'])[2]")
    private WebElement buttonOkay;

    @FindBy(xpath = "//*[contains(@class,'widgetSearchBtn')]")
    private WebElement buttonSearch;

    @FindBy(xpath = "//*[contains(@class,'journey-title')]")
    private WebElement journeyTitle;

    @FindBy(xpath = "(//*[@class='appliedFilter'])/li")
    private WebElement chipsFilterApplied;

    @FindBy(xpath = "(//*[@class='linkText pointer'])[1]")
    private WebElement showMorePopularFilter;

    //only february date
    public WebElement getDateStart(String dateStart) {
        return webDriver.findElement(By.xpath("(//*[@class='dateInnerCell']//p[.='" + dateStart + "'])[1]"));
    }

    //only february date
    public WebElement getDateEnd(String dateEnd) {
        return webDriver.findElement(By.xpath("(//*[@class='dateInnerCell']//p[.='" + dateEnd + "'])[1]"));
    }


    public WebElement getCheckboxFilterSearch(int i) {
        return webDriver.findElement(By.xpath("(//*[@id='listingFilterCheckbox'])[" + i + "]"));
    }

    public WebElement getFilterApplied(int i) {
        return webDriver.findElement(By.xpath("(//*[@class='appliedFilter'])/li[" + i + "]"));
    }

    public void setFromCity(String city) {
        fieldFromCity.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(inputFromCity));
        inputFromCity.sendKeys(city);
        delayHelper(5000);
        webDriverWait.until(ExpectedConditions.visibilityOf(resultDestination));
        resultDestination.click();
        dataStored.fromCity = city;
    }

    public void setToCity(String city) {
        fieldToCity.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(inputToCity));
        inputToCity.sendKeys(city);
        delayHelper(5000);
        webDriverWait.until(ExpectedConditions.visibilityOf(resultDestination));
        resultDestination.click();
        dataStored.toCity = city;
    }

    public void setDateStart(String dateStart) {
        webDriverWait.until(ExpectedConditions.visibilityOf(getDateStart(dateStart)));
        getDateStart(dateStart).click();
    }

    public void setDateEnd(String dateEnd) {
        fieldReturn.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(getDateEnd(dateEnd)));
        getDateEnd(dateEnd).click();
    }

    public void clickSearch() {
        buttonSearch.click();
        delayHelper(20000);
        closePopUpOverlay();
    }

    public void closePopUpOverlay() {
        if (verifyElementPresent(By.className("commonOverlay"))) {
            buttonOkay.click();
            System.out.println("Pop Up Overlay Closed");
        } else {
            System.out.println("Pop Up Overlay Not Displayed");
        }
    }

    public void verifyResultSearch() {
        String getResult = webDriver.findElement(By.xpath("//*[contains(@class,'journey-title')]")).getText();
        System.out.println("Result Search From City : " + dataStored.fromCity);
        System.out.println("Result Search To City : " + dataStored.toCity);
        boolean isSearch = getResult.contains(dataStored.fromCity) && getResult.contains(dataStored.toCity);
        Assert.assertTrue(isSearch, "Search did not display the expected cities.");
    }

    public void selectCheckboxFilter(String filter) {
        int getRowFilter = webDriver.findElements(By.xpath("//*[contains(@class,'checkboxTitle')]")).size();
        System.out.println("Row Filter: " + getRowFilter);
        for (int i = 1; i <= getRowFilter; i++) {
            String getFilter = webDriver.findElement(By.xpath("(//*[@class='checkboxTitle'])[" + i + "]")).getText();
            if (getFilter.contains(filter)) {
                getCheckboxFilterSearch(i).click();
                break;
            }
        }
        delayHelper(3000);
    }

    public void showMorePopularFilter(){
        webDriverWait.until(ExpectedConditions.visibilityOf(showMorePopularFilter));
        showMorePopularFilter.click();
        delayHelper(3000);
    }

    public void verifyFilterApplied(String filterApplied) {
        boolean isFilterApplied = verifyElementPresent(By.xpath("(//*[@class='appliedFilter'])/li"));
        Assert.assertTrue(isFilterApplied, "Filter not applied.");

        int getSizeFilterApplied = webDriver.findElements(By.xpath("(//*[@class='appliedFilter'])/li")).size();
        System.out.println("Size Filter Applied: " + getSizeFilterApplied);
        for (int i = 1; i <= getSizeFilterApplied; i++) {
            String getFilter = webDriver.findElement(By.xpath("(//*[@class='appliedFilter'])/li[" + i + "]")).getText();
            if (getFilter.contains(filterApplied)) {
                System.out.println("Success Filter Applied: " + getFilter);
                break;
            }
        }
    }

    public void delayHelper(int delay) {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean verifyElementPresent(By by) {
        List<WebElement> elements = webDriver.findElements(by);
        return elements != null && elements.size() > 0;
    }
}
