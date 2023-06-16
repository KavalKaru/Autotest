package com.calculator;

import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class LeasingCalculatorPage {


    private static final Logger LOGGER = LoggerFactory.getLogger(LeasingCalculatorPage.class);
    public SelenideElement linkAcceptPirukas = $("a[id='acceptPirukas']");

    public SelenideElement accountTypeCivilian = $(byText("eraisikuna"));

    public SelenideElement accountTypeCorporation = $(byText("juriidilise isikuna"));


    public SelenideElement inputPrice = $("#price");


    public SelenideElement selectVatScheduling = $("#vat_scheduling");


    public SelenideElement monthlyPayment = $x("/html/body/div[1]/div[6]/div[5]/div[1]/div/div[3]/div[1]/div[1]/div[3]/div[2]");

    public SelenideElement inputInitial = $("#initial");

    public SelenideElement inputReminder = $("#reminder");


    public SelenideElement inputInterestRate = $("#interest_rate");

    public SelenideElement selectYears = $("select[name='years']");

    public SelenideElement selectMonths = $("select[name='months']");


    public void acceptCookies() {
        if (linkAcceptPirukas.isDisplayed()) {
            linkAcceptPirukas.click();
            LOGGER.info("Accepted cookies");
        } else {
            LOGGER.info("Skipped accepting cookies - assuming they were aleady accepted by previous test");
        }
    }

    public void setInputPrice(double amount) {
        inputPrice.clear();
        inputPrice.sendKeys(String.valueOf(amount));
        LOGGER.info("Set input price to: " + amount);
    }

    public void accountTypeCorp(Boolean corporate) {
        if (corporate) {
            accountTypeCorporation.click();
            LOGGER.info("Account type is: Juriidiline isik");
        } else {
            accountTypeCivilian.click();
            LOGGER.info("Account type is: Eraisik");
        }
    }

    public void vatScheduling(int value) {
        selectVatScheduling.selectOption(value);
        LOGGER.info("Set option for vatScheduling: " + value);
    }

    public void setInitialDeposit(double amount) {
        inputInitial.clear();
        inputInitial.sendKeys(String.valueOf(amount));
        LOGGER.info("Input deposit set to: " + amount + "€");
    }

    public void setSalvagaValue(double salvageAmount) {
        inputReminder.clear();
        inputReminder.sendKeys(String.valueOf(salvageAmount));
        LOGGER.info("Salvage value amount is set to: " + salvageAmount + "€");
    }

    public void setInterestRate(double interestRate) {
        inputInterestRate.clear();
        inputInterestRate.sendKeys(String.valueOf(interestRate));
        LOGGER.info("Interest rate is set to: " + interestRate + "%");
    }

    public void leasingPeriodYears(int value) {
        selectYears.selectOption(value);
        LOGGER.info("Set option for leasing period years: " + value);
    }

    public void leasingPeriodMonths(int value) {
        selectMonths.selectOption(value); //max 7 (sometimes 6)
        LOGGER.info("Set option for leasing period months: " + value); //max 12
    }


    public void getMonthlyPayment(double expectedAmount) {
        String value = monthlyPayment.getText();
        double amount = Double.parseDouble(value);
        LOGGER.info("Calculated monthly payment amount is: " + amount + "€");
        Assert.assertEquals(amount, expectedAmount);
    }


}
