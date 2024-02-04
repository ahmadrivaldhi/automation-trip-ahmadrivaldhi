package org.trip.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    @BeforeSuite
    public void globalSetUp(){
        System.out.println("************************************** Test Execution Started *****************************");
    }

    @AfterSuite
    public void globalTearDown(){
        System.out.println("************************************** Test Execution Finished *****************************");
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    @BeforeTest
    public void setup(){
        initWebDriver();
        openBrowser();
    }

    @AfterTest
    public void tearDownTest(){
    }

    public void initWebDriver(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void openBrowser(){
        webDriver.navigate().to("https://www.makemytrip.com");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
