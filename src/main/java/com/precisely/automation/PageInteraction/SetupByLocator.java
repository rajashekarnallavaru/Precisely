package com.precisely.automation.PageInteraction;

import com.precisely.automation.Builder.ActionParams;
import org.openqa.selenium.By;

public class SetupByLocator {

    public SetupByLocator() {

    }

    SetupLocatorPath setupLocatorPath = new SetupLocatorPath();

    public By locatorParser(String byType, String locator) {
        By loc = By.id(locator);
        switch (byType) {
        case "name":
            loc = By.name(locator);
            break;
        case "linkText":
            loc = By.linkText(locator);
            break;
        case "xpath":
            loc = By.xpath(locator);
            break;
        case "className":
            loc = By.className(locator);
            break;
        case "cssSelector":
            loc = By.cssSelector(locator);
            break;
        case "partialLinkText":
            loc = By.partialLinkText(locator);
            break;
        case "tagName":
            loc = By.tagName(locator);
            break;
        }
        return loc;
    }

    public By getByLocator(ActionParams.Params locatorParams) { // set up the By statement eg By.cssSelector(rawLocator)
        String byType = locatorParams.getByType(); // defaults to xpath eg By.xpath("");
        String locator = setupLocatorPath.getPropertyLocator(locatorParams.getFileName(), locatorParams.getLocator());
        int position = locatorParams.getPosition(); // for drag and drop element position. Defaults to 0
        if (position > 0) {
            locator = "(" + locator + ")[" + position + "]";
        }
        return locatorParser(byType, locator);
    }

}
