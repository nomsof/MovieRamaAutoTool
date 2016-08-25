package com.workable.movierama.controller;

import java.util.List;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.workable.movierama.controller.LocatorController.Locators;
import com.workable.movierama.session.constants.BrowserConstants;

public class WebController {
	private static final String url = "http://workable-movierama.herokuapp.com/";

	private WebDriver driver;

	public WebController(WebDriver driver) {
		this.driver = driver;
		driver.get(url);
	}

	public void mainPageURL() {
		driver.get(url);
	}

	public WebElement waitForElementPresent(Locators locatorType, String locator, long timeOutInSeconds) {
		try {
			return new WebDriverWait(driver, timeOutInSeconds).until(
					ExpectedConditions.presenceOfElementLocated(LocatorController.findByLocator(locatorType, locator)));
		} catch (TimeoutException e) {
			throw new ElementNotVisibleException("The element is not present", e);
		}
	}

	public void typeAfterClear(Locators locatorType, String locator, String text) {
		WebElement input = waitForElementPresent(locatorType, locator, BrowserConstants.SLEEP_TIME);
		input.clear();
		input.sendKeys(new CharSequence[] { text });
		input.submit();
	}

	public void deleteInput(Locators locatorType, String locator) {
		WebElement input = waitForElementPresent(locatorType, locator, BrowserConstants.SLEEP_TIME);
		input.sendKeys(Keys.CLEAR);
		input.submit();
	}

	public List<WebElement> getElementsByLocator(Locators locatorType, String locator) {
		return driver.findElements(LocatorController.findByLocator(locatorType, locator));
	}

	public List<WebElement> getElementsByLocator(Locators locatorType, String locator, WebElement element) {
		return element.findElements(LocatorController.findByLocator(locatorType, locator));
	}

	public WebElement getElementByLocator(Locators locatorType, String locator) {
		return driver.findElement(LocatorController.findByLocator(locatorType, locator));
	}

	public WebElement getElementByLocator(Locators locatorType, String locator, WebElement element) {
		return element.findElement(LocatorController.findByLocator(locatorType, locator));
	}
}
