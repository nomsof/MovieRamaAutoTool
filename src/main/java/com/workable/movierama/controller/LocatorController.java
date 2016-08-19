package com.workable.movierama.controller;

import org.openqa.selenium.By;

public class LocatorController {

	public enum Locators {
		XPATH, CSS, NAME, LINK, ID, TAGNAME, CLASSNAME;
	}

	public static By findByLocator(Locators locatorType, String locator) {
		switch (locatorType) {
		case XPATH:
			return By.xpath(locator);
		case CSS:
			return By.cssSelector(locator);
		case NAME:
			return By.name(locator);
		case LINK:
			return By.linkText(locator);
		case ID:
			return By.id(locator);
		case TAGNAME:
			return By.tagName(locator);
		case CLASSNAME:
			return By.className(locator);
		default:
			return By.id(locator);
		}
	}
}
