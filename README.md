# automation-trip-ahmadrivaldhi
Nama : Ahmad Rivaldhi
Subject: Project 2 Automation MakeMyTrip

Scenario Test:
- Search Flight Bangkok to Singapura
- Search Flight With Filter Refundable Jakarta to London
- Search Flight With Multiple Popular Filter Jakarta to London

Description Package File:
- main/java/automation.pages/base/BasePage [for base page test]
- main/java/automation.pages/flight/FlightPage [contains start locator and functions and assertions used for tests]
- test/java/org.trip/base/BaseTest [for WebDriver init base test]
- test/java/org.trip/flight/FlightTest [contains test scenarios that can be run one by one or run at once from class]
- test/java/org.trip/resources/testng.xml [running from testng xml file]

how running:
- clone this repo
- reload maven project for downloading dependencies
- use terminal 'mvn test' for running all test scenario
- running one by one can from FlightTest.java file
