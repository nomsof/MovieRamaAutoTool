package com.workable.movierama.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.workable.movierama.controller.WebController;
import com.workable.movierama.controller.LocatorController.Locators;
import com.workable.movierama.pages.constants.UILocators;

public class NextPage extends MainPage {
	public static final String NEXT_PAGE_BUTTON = "next";

	public NextPage(WebController webController) {
		super(webController);
	}

	public NextPage open() {
		List<WebElement> elements = new ArrayList<WebElement>();
		WebElement mainContainer = webController.getElementByLocator(Locators.CSS,
				UILocators.CSS_LOCATOR_MAIN_CONTAINER);
		List<WebElement> ngScopes = webController.getElementsByLocator(Locators.CSS, UILocators.CSS_LOCATOR_NG_SCOPE,
				mainContainer);
		for (WebElement ngScope : ngScopes) {
			boolean selected = false;
			elements = webController.getElementsByLocator(Locators.CSS, UILocators.CSS_LOCATOR_A_NG_SCOPE, ngScope);
			for (WebElement element : elements) {
				if (element.getText().toLowerCase().equals(NEXT_PAGE_BUTTON)) {
					element.click();
					selected = true;
					break;
				}
			}
			if (selected) {
				break;
			}
		}
		return this;
	}
}
