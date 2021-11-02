package com.precisely.automation.step_definitions.preciselyTestSteps;

import com.precisely.automation.helpers.Util;
import com.precisely.automation.pageObjects.Infogix;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;

public class TestSteps {


    Util util = new Util();
    Infogix infogix = new Infogix();

    @Given("the user navigate to url")
    public void theUserNavigateToUrl() {
        util.navigateToUrl("https://www.infogix.com/");
    }

    @When("user clicks on contact from menu")
    public void userClicksOnContactFromMenu() {
        infogix.clickOnContact();
    }

    @When("the user insert {string} as first name")
    public void theUserInsertAsFirstName(String firstName) {
        final String firstRandom = RandomStringUtils.randomNumeric(4) + " " + RandomStringUtils.randomAlphabetic(4);
        infogix.insertTextFirstName(firstName + firstRandom);
    }

    @When("the user insert {string} as last name")
    public void theUserInsertAsLastName(String lastName) {
        final String lastRandom = RandomStringUtils.randomNumeric(4) + " " + RandomStringUtils.randomAlphabetic(4);
        infogix.insertTextLastName(lastName + lastRandom);
    }

    @When("the user insert {string} as company name")
    public void theUserInsertAsCompany(String companyName) {
        final String companyRandom = RandomStringUtils.randomNumeric(4) + " " + RandomStringUtils.randomAlphabetic(4);
        infogix.insertTextCompanyName(companyName + companyRandom);
    }

    @When("the user insert {string} as email")
    public void theUserInsertAsEmail(String email) {
        infogix.insertTextEmail(email);
    }

    @When("the user insert {string} as phone number")
    public void theUserInsertAsPhoneNumber(String phoneNumber) {
        infogix.insertPhoneNumber(phoneNumber);
    }

    @When("the user select {string} as country")
    public void theUserSelectAsCountry(String country) {
        infogix.selectCountry(country);
    }

    @When("the user select {string} as industry")
    public void theUserSelectAsIndustry(String industry) {
        infogix.selectIndustry(industry);
    }

    @When("the user insert {string} as comment")
    public void theUserInsertAsComment(String comment) {
        infogix.insertComment(comment);
    }

    @And("the user click on agree checkbox")
    public void theUserClickOnAgreeCheckbox() {
        infogix.clickOnAgreeCheckbox();
    }

    @And("the user click on submit button")
    public void theUserClickOnSubmitButton() {
        infogix.clickOnSubmitButton();
    }

    @Then("check for thank you text on screen")
    public void checkForThankYouTextOnScreen() {
        infogix.expectForThankYouText();
    }

    @When("the user clicks on search button")
    public void theUserClickOnSearchButton() {
        infogix.clickOnSearchButton();
    }

    @When("the user enter {string} in search text field")
    public void theUserEnterInSearchTextField(String searchText) {
        infogix.enterTextInSearchField(searchText);
    }

    @When("the user clicks on {string} from search results")
    public void theUserClicksOnFromSearchResults(String searchResultText) {
        infogix.clickOnSearchResults(searchResultText);
    }

    @Then("verify the link from text should be {string}")
    public void verifyTheLinkFromTextShouldBe(String linkText) {
        infogix.verifyTheLinkText(linkText);
    }

}
