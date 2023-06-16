package com.calculator;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;


public class LeasingCalculatorTest {
    LeasingCalculatorPage leasingCalculator = new LeasingCalculatorPage();


    @Test
    @Description("Base test that checks if monthly payment value is displayed on page")
    public void baseTest() {
        leasingCalculator.getMonthlyPayment(206.37);
    }

    @Test
    @Description("Test is checking the leasing calculator for private normal person while changing inputs")
    public void forNormalPerson() throws InterruptedException {
        calculateLeasingForNormalPerson(7500.43, 1200, 600, 9, 3, 7);
        //TimeUnit.SECONDS.sleep(10); to validate manually from UI the calculation you can use sleep
        leasingCalculator.getMonthlyPayment(160.08);
    }

    @Test
    @Description("Test is checking the leasing calculator for corporate while changing inputs")
    public void forCorporatePerson() {
        calculateLeasingForCorporatePerson(1, 7500.43, 1000, 800, 5, 4, 6);
        leasingCalculator.getMonthlyPayment(98.21);
    }

    @Test
    @Description("Test is checking the leasing calculator for corporate while changing inputs")
    public void forCorporatePersonV2() {
        calculateLeasingForCorporatePerson(2, 7500.43, 1000, 800, 5, 4, 6);
        leasingCalculator.getMonthlyPayment(97.81);
    }


    @BeforeClass
    public static void setUpAll() {
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeMethod
    public void setUp() {
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        open("https://www.lhv.ee/et/liising#kalkulaator");
        leasingCalculator.acceptCookies();
    }

    private void calculateLeasingForCorporatePerson(int vatChedulingOption, double carPrice, double initialDepositAmount, double salvageAmount, double interestRate, int leasingPeriodYearsValue, int leasingPeriodMonthsValue) {
        leasingCalculator.accountTypeCorp(true);
        leasingCalculator.vatScheduling(vatChedulingOption); // it can be 1,2 or 3
        leasingCalculator.setInputPrice(carPrice);
        leasingCalculator.setSalvagaValue(salvageAmount);
        leasingCalculator.setInitialDeposit(initialDepositAmount);
        leasingCalculator.setInterestRate(interestRate);
        leasingCalculator.leasingPeriodYears(leasingPeriodYearsValue);
        leasingCalculator.leasingPeriodMonths(leasingPeriodMonthsValue);
    }

    private void calculateLeasingForNormalPerson(double carPrice, double initialDepositAmount, double salvageAmount, double interestRate, int leasingPeriodYearsValue, int leasingPeriodMonthsValue) {
        leasingCalculator.accountTypeCorp(false);
        leasingCalculator.setInputPrice(carPrice);
        leasingCalculator.setInitialDeposit(initialDepositAmount);
        leasingCalculator.setSalvagaValue(salvageAmount);
        leasingCalculator.setInterestRate(interestRate);
        leasingCalculator.leasingPeriodYears(leasingPeriodYearsValue);
        leasingCalculator.leasingPeriodMonths(leasingPeriodMonthsValue);
    }
}




