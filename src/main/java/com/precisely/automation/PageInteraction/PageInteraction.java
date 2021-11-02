package com.precisely.automation.PageInteraction;

import com.precisely.automation.Builder.ActionParams;
import com.precisely.automation.browser.Driver;
import com.precisely.automation.constants.Constants;
import com.precisely.automation.helpers.Util;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import static com.precisely.automation.browser.Driver.driver;

public class PageInteraction {
    private WebDriver webDriver = Driver.driver;
    SetupAction actionSetup = new SetupAction();
    SetupExpectedCondition conditionSetup = new SetupExpectedCondition();
    SetupByLocator locatorSetup = new SetupByLocator();
    Actions action = new Actions(webDriver);
    JavascriptExecutor runJS = ((JavascriptExecutor) webDriver);

    public PageInteraction() {

    }

    public void expectLocatorPresent(ActionParams.Params methodParams) {
        actionSetup.presence(methodParams);
    }

    public void expectAllLocatorsPresent(ActionParams.Params methodParams) {
        actionSetup.presenceOfAll(methodParams);
    }

    public void expectLocatorVisible(ActionParams.Params methodParams) {
        actionSetup.visibility(methodParams);
    }

    public void expectLocatorSelected(ActionParams.Params methodParams) {
        actionSetup.elementToBeSelected(methodParams);
    }

    public void expectLocatorInvisible(ActionParams.Params methodParams) {
        actionSetup.invisibility(methodParams);
    }

    public void expectNumberOfElementsToBeMoreThan(ActionParams.Params methodParams, int sizeOfTotalElements) {
        actionSetup.numberOfElementsToBeMoreThan(methodParams,sizeOfTotalElements);
    }

    public void expectLocatorTextToBe(ActionParams.Params methodParams) {
        actionSetup.presenceOfTextToBe(methodParams);
    }

    public boolean expectStringInPageSource(String text) {
        String pageSource = webDriver.getPageSource();
        return pageSource.contains(text);
    }

    public void expectStringNotPresentInPageSource(String text) {
        String pageSource = webDriver.getPageSource();
        Assert.assertFalse("String: '" + text + "' could be found in page source.", pageSource.contains(text));
    }

    public void expectStringInUrl(ActionParams.Params methodParams) {
        actionSetup.stringInUrl(methodParams);
    }

    public void expectMultipleWindows(ActionParams.Params methodParams) {
        actionSetup.multipleWindows(methodParams);
    }

    public Boolean isPresent(ActionParams.Params methodParams) {
        try {
            actionSetup.presenceBooleanReturned(methodParams);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean isSelected(ActionParams.Params methodParams) {
        WebElement element = actionSetup.visibility(methodParams);
        return (element).isSelected();
    }

    public Boolean isEnabled(ActionParams.Params methodParams) {
        WebElement element = actionSetup.visibility(methodParams);
        return (element).isEnabled();
    }

    public Boolean isDisplayed(ActionParams.Params methodParams) {
        WebElement element = actionSetup.presenceBooleanReturned(methodParams);
        return (element).isDisplayed();
    }

    public Boolean isPresentAndDisplayed(ActionParams.Params methodParams) {
        try {
            actionSetup.visibility(methodParams);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCountPositive(ActionParams.Params methodParams) {
        return getCount(methodParams) > 0;
    }

    public int getCount(ActionParams.Params methodParams) {
        By byLocator = locatorSetup.getByLocator(methodParams);
        isPresent(methodParams);
        return webDriver.findElements(byLocator).size();
    }

    public String getElementText(ActionParams.Params methodParams) {
        String returnStr = null;
        Boolean byJS = methodParams.getByJS(); // defaults to false
        if (byJS) {
            returnStr = (String) runJS.executeScript("return arguments[0].innerText", actionSetup.presence(methodParams));
        } else {
            try {
                returnStr = actionSetup.presence(methodParams).getText();
            } catch(StaleElementReferenceException ex) {
                returnStr = actionSetup.presence(methodParams).getText();
            }
        }
        return returnStr.trim();
    }

    public String getAttributeValue(ActionParams.Params methodParams) {
        String attributeName = methodParams.getAttributeName();
        WebElement attributeValue = actionSetup.presence(methodParams);
        return attributeValue.getAttribute(attributeName).trim();
    }

    public Dimension getDimension(ActionParams.Params methodParams) {
        WebElement locator = actionSetup.presence(methodParams);
        Dimension size = locator.getSize();
        return size;
    }

    public List<WebElement> getListElements(ActionParams.Params methodParams) {
        return conditionSetup.setupExpectedConditionList("presenceOfAll", methodParams);
    }

    public void actionScroll(ActionParams.Params methodParams) {
        WebElement scroll2Element = actionSetup.presence(methodParams);
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        runJS.executeScript(scrollElementIntoMiddle, scroll2Element);
    }

    public void actionMove(ActionParams.Params methodParams) {
        WebElement move2Element = actionSetup.presence(methodParams);
        action.moveToElement(move2Element).build().perform();
    }

    public void actionClick(ActionParams.Params methodParams) {
        actionClick(true, methodParams);
    }

    public void actionClick(boolean lazyload, ActionParams.Params methodParams) {
        Boolean byJS = methodParams.getByJS(); // defaults to false
        if (byJS) {
            WebElement element2Click = actionSetup.presence(methodParams);
            System.out.println("## click by js");
            runJS.executeScript("arguments[0].click();", element2Click);
        } else {
            WebElement element2Click = actionSetup.clickable(methodParams);
            element2Click.click();
        }
    }

    public void actionSubmit(ActionParams.Params methodParams) {
        WebElement submitElement = actionSetup.clickable(methodParams);
        submitElement.click();
    }

    public void actionInputAndSelectFromDropdown(ActionParams.Params methodParams) {
        Boolean byJS = methodParams.getByJS();
        String text = methodParams.getText();
        WebElement enterText2Element = actionSetup.visibility(methodParams);
        if (byJS) {
            enterText2Element = actionSetup.presence(methodParams);
            runJS.executeScript("arguments[0].value='" + text + "';", enterText2Element);
        } else {
            enterText2Element.clear();
            enterText2Element.sendKeys(text);
        }
        Util.pause(Constants.WaitingTime.MEDIUM);
        enterText2Element.sendKeys(Keys.DOWN);
        enterText2Element.sendKeys(Keys.ENTER);
    }

    public void actionInputText(ActionParams.Params methodParams) {
        actionInputText(true, methodParams);
    }

    public void actionInputText(Boolean clearText, ActionParams.Params methodParams) {
        Boolean byJS = methodParams.getByJS();
        String text = methodParams.getText();
        Boolean typeReturn = methodParams.getReturnKey();
        WebElement enterText2Element = actionSetup.visibility(methodParams);
        if (byJS) {
            enterText2Element = actionSetup.presence(methodParams);
            runJS.executeScript("arguments[0].value = arguments[1];", enterText2Element, text);
        } else {
            if (clearText) {
                enterText2Element.clear();
            }
            enterText2Element.sendKeys(text);
        }
        if (typeReturn) {
            enterText2Element.sendKeys(Keys.ENTER);
        }

    }

    public void actionClearInputText(ActionParams.Params methodParams) {
        Boolean typeReturn = methodParams.getReturnKey();
        WebElement enterText2Element = actionSetup.visibility(methodParams);
        enterText2Element.clear();
        if (typeReturn) {
            enterText2Element.sendKeys(Keys.ENTER);
        }
    }

    public void actionSelectOptionByText(ActionParams.Params methodParams) {
        String optionText = methodParams.getText();
        WebElement optionToSelect = actionSetup.clickable(methodParams);
        Select dropdown = new Select(optionToSelect);
        dropdown.selectByVisibleText(optionText);
    }

    public void actionSelectOptionByValue(ActionParams.Params methodParams) {
        String optionValue = methodParams.getText();
        WebElement optionToSelect = actionSetup.clickable(methodParams);
        Select dropdown = new Select(optionToSelect);
        dropdown.selectByValue(optionValue);
    }

    /**
     * @param methodParams - methodParams from ActionParams.
     * @return - text of first selected option in a dropdown.
     */
    public String getFirstSelectedOptionText(ActionParams.Params methodParams) {
        WebElement optionToSelect = actionSetup.clickable(methodParams);
        Select dropdown = new Select(optionToSelect);
        return dropdown.getFirstSelectedOption().getText();
    }

    /**
     * @param methodParams - methodParams from ActionParams.
     * @return - value of value attribute of first selected option in a dropdown.
     */
    public String getFirstSelectedOptionValue(ActionParams.Params methodParams) {
        WebElement optionToSelect = actionSetup.clickable(methodParams);
        Select dropdown = new Select(optionToSelect);
        return dropdown.getFirstSelectedOption().getAttribute("value");
    }

    public void actionDragNDrop(ActionParams.Params dragParams, ActionParams.Params dropParams) {
        WebElement dragFrom = actionSetup.clickable(dragParams);
        WebElement dropTo = actionSetup.clickable(dropParams);
        action.dragAndDrop(dragFrom, dropTo).build().perform();
    }

    public void actionSwitchToFrame(ActionParams.Params methodParams) {
        actionScroll(methodParams);
        actionSetup.frameAvailableSwitchToIt(methodParams);
    }

    public void actionSwitchToWindow(ActionParams.Params methodParams) {
        methodParams = methodParams.setPosition(2); // set number of windows = 2
        actionSetup.multipleWindows(methodParams);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public String getClipboardText(ActionParams.Params methodParams) throws IOException, UnsupportedFlavorException {
        actionClick(methodParams);
        System.setProperty("apple.awt.UIElement", "true");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        return (String) clipboard.getData(DataFlavor.stringFlavor);
    }

    public void actionSwitchToDefaultFrame() {
        webDriver.switchTo().defaultContent();
    }

    public void actionScrollToBottom() {
        runJS.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void actionScrollToMiddle() {
        runJS.executeScript("window.scrollTo(0,300)");
    }

    public void actionScrollToTop() {
        runJS.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    }

    //=============================================================================================================

}