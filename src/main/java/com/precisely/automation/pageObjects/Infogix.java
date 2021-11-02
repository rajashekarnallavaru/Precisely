package com.precisely.automation.pageObjects;

import com.precisely.automation.Builder.ActionParams;
import com.precisely.automation.PageInteraction.PageInteraction;
import org.junit.Assert;

public class Infogix {

    PageInteraction pageActions = new PageInteraction();


    // LOCATORS

    private ActionParams.Params contact() {
        return new ActionParams.Params().setByType("xpath").setLocator("//ul[@id='menu-utility']/li/a[text()='Contact']");
    }

    private ActionParams.Params firstName() {
        return new ActionParams.Params().setByType("id").setLocator("FirstName");
    }

    private ActionParams.Params lastName() {
        return new ActionParams.Params().setByType("id").setLocator("LastName");
    }

    private ActionParams.Params companyName() {
        return new ActionParams.Params().setByType("id").setLocator("Company");
    }

    private ActionParams.Params workEmail() {
        return new ActionParams.Params().setByType("id").setLocator("Email");
    }

    private ActionParams.Params phoneNumber() {
        return new ActionParams.Params().setByType("id").setLocator("Phone");
    }

    private ActionParams.Params hqLocationCountryDropDown() {
        return new ActionParams.Params().setByType("id").setLocator("HQ_Location_Country__c");
    }

    private ActionParams.Params industryDropDown() {
        return new ActionParams.Params().setByType("id").setLocator("Industry__c");
    }

    private ActionParams.Params comments() {
        return new ActionParams.Params().setByType("id").setLocator("Next_Step_Comments__c");
    }

    private ActionParams.Params agreeCheckBox() {
        return new ActionParams.Params().setByType("id").setLocator("LblConsent_to_Processing__c");
    }

    private ActionParams.Params submitButton() {
        return new ActionParams.Params().setByType("xpath").setLocator("//button[@class='mktoButton']");
    }

    private ActionParams.Params thankYouText() {
        return new ActionParams.Params().setByType("xpath").setLocator("//div[@class='callout-card']/h1[contains(text(),'Thank You!')]");
    }

    private ActionParams.Params searchButton() {
        return new ActionParams.Params().setByType("xpath").setLocator("//nav[@class='utility']/a[@class='search-site']");
    }

    private ActionParams.Params searchTextField() {
        return new ActionParams.Params().setByType("xpath").setLocator("//input[@class='searchfor']");
    }

    private ActionParams.Params searchResults(String searchText) {
        return new ActionParams.Params().setByType("xpath").setLocator("//div[@class='article-list']//li//div[@class='details']//a[contains(text(),'"+searchText+"')]");
    }

    private ActionParams.Params linkFromText(String linkText) {
        return new ActionParams.Params().setByType("xpath").setLocator("//div[@class='info']//a[text()='" + linkText + "']");
    }


    // METHODS

    public void clickOnContact() {
        pageActions.actionClick(contact());
    }

    public void insertTextFirstName(String firstName) {
        pageActions.actionInputText(firstName().setText(firstName));
    }

    public void insertTextLastName(String lastName) {
        pageActions.actionInputText(lastName().setText(lastName));
    }

    public void insertTextCompanyName(String companyName) {
        pageActions.actionInputText(companyName().setText(companyName));
    }

    public void insertTextEmail(String email) {
        pageActions.actionInputText(workEmail().setText(email));
    }

    public void insertPhoneNumber(String phoneNumber) {
        pageActions.actionInputText(phoneNumber().setText(phoneNumber));
    }

    public void selectCountry(String country) {
        pageActions.actionSelectOptionByText(hqLocationCountryDropDown().setText(country));
    }

    public void selectIndustry(String industry) {
        pageActions.actionSelectOptionByText(industryDropDown().setText(industry));
    }

    public void insertComment(String comment) {
        pageActions.actionInputText(comments().setText(comment));
    }

    public void clickOnAgreeCheckbox() {
        pageActions.actionClick(agreeCheckBox());
    }

    public void clickOnSubmitButton() {
        pageActions.actionClick(submitButton());
    }

    public void expectForThankYouText() {
        pageActions.expectLocatorVisible(thankYouText());
    }

    public void clickOnSearchButton() {
        pageActions.actionClick(searchButton());
    }

    public void enterTextInSearchField(String searchText) {
        pageActions.actionInputText(searchTextField().setText(searchText));
        pageActions.actionSubmit(searchTextField());
    }

    public void clickOnSearchResults(String searchResult) {
        pageActions.actionClick(searchResults(searchResult));
    }

    public void verifyTheLinkText(String link) {
        Assert.assertEquals(link, pageActions.getAttributeValue(linkFromText(link).setAttributeName("href")));
    }

}
