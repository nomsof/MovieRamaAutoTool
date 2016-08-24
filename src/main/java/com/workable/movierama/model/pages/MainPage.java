package com.workable.movierama.model.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.workable.movierama.controller.LocatorController.Locators;
import com.workable.movierama.controller.WebController;
import com.workable.movierama.model.Movie;
import com.workable.movierama.model.pages.constants.UILocators;
import com.workable.movierama.session.constants.BrowserConstants;

public class MainPage {
    private static final String NEXT_PAGE_BUTTON = "next";
    private static final String PREVIOUS_PAGE_BUTTON = "previous";

    private WebController webController;

    public MainPage(WebController webController) {
        this.webController = webController;
    }

    public MainPage returnToMainPage() {
        webController.mainPageURL();
        return this;
    }

    public MainPage search(String text) {
        WebElement element = webController.waitForElementPresent(Locators.CSS, UILocators.CSS_LOCATOR_SEARCH,
                BrowserConstants.SLEEP_TIME);
        element.clear();
        element.sendKeys(new CharSequence[] { text });
        element.submit();
        return this;
    }

    public MainPage clearSearch() {
        webController.getElementByLocator(Locators.CSS, UILocators.CSS_LOCATOR_SEARCH_CLEAR).click();
        return this;
    }

    public MainPage selectRandomPageNumber() {
        WebElement mainContainer = webController.getElementByLocator(Locators.CSS,
                UILocators.CSS_LOCATOR_MAIN_CONTAINER);
        List<WebElement> ngScopeElements = webController.getElementsByLocator(Locators.CSS,
                UILocators.CSS_LOCATOR_NG_SCOPE, mainContainer);
        for (WebElement scope : ngScopeElements) {
            boolean selected = false;
            List<WebElement> spanElements = webController.getElementsByLocator(Locators.CSS,
                    UILocators.CSS_LOCATOR_SPAN_NG_SCOPE, scope);

            if (spanElements != null && spanElements.size() > 0) {
                for (WebElement span : spanElements) {
                    List<WebElement> pageNumbers = webController.getElementsByLocator(Locators.CSS,
                            UILocators.CSS_LOCATOR_A_NG_SCOPE_BINDING, span);
                    for (WebElement pageNumber : pageNumbers) {
                        if (!pageNumber.isSelected()) {
                            pageNumber.click();
                            selected = true;
                            break;
                        }
                    }
                    if (selected) {
                        break;
                    }
                }
                if (selected) {
                    break;
                }
            }
        }
        return this;
    }

    public MainPage selectRandomResultsSize() {
        WebElement element = webController.waitForElementPresent(Locators.XPATH, UILocators.XPATH_LOCATOR_SELECT,
                BrowserConstants.SLEEP_TIME);
        Select select = new Select(element);
        List<WebElement> options = select.getOptions();
        select.selectByIndex((new Random()).nextInt(options.size()));
        return this;
    }

    public int getResultsSizeSelection() {
        WebElement element = webController.waitForElementPresent(Locators.XPATH, UILocators.XPATH_LOCATOR_SELECT,
                BrowserConstants.SLEEP_TIME);
        Select select = new Select(element);
        return Integer.parseInt(select.getFirstSelectedOption().getText());
    }

    public MainPage selectResultsSize(String option) {
        WebElement element = webController.waitForElementPresent(Locators.XPATH, UILocators.XPATH_LOCATOR_SELECT,
                BrowserConstants.SLEEP_TIME);
        Select select = new Select(element);
        select.selectByVisibleText(option);
        return this;
    }

    public List<WebElement> getSystemResponse() {
        List<WebElement> elements = new ArrayList<WebElement>();
        WebElement mainContainer = webController.getElementByLocator(Locators.CSS,
                UILocators.CSS_LOCATOR_MAIN_CONTAINER);
        mainContainer = webController.getElementByLocator(Locators.CSS, UILocators.CSS_LOCATOR_NG_SCOPE, mainContainer);
        elements = webController.getElementsByLocator(Locators.CSS, UILocators.CSS_LOCATOR_NG_BINDING, mainContainer);
        return elements;
    }

    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        WebElement mainContainer = webController.getElementByLocator(Locators.CSS,
                UILocators.CSS_LOCATOR_MAIN_CONTAINER);
        mainContainer = webController.getElementByLocator(Locators.CSS, UILocators.CSS_LOCATOR_NG_SCOPE, mainContainer);
        List<WebElement> elements = webController.getElementsByLocator(Locators.CSS, UILocators.CSS_LOCATOR_MOVIE,
                mainContainer);
        for (WebElement element : elements) {
            Movie movie = new Movie();
            movie.setTitle(webController.getElementByLocator(Locators.CSS, UILocators.CSS_LOCATOR_MOVIE_HEADER, element)
                    .getText());
            movie.setReview(webController
                    .getElementByLocator(Locators.CSS, UILocators.CSS_LOCATOR_MOVIE_REVIEWS, element).getText());

            List<WebElement> data = webController.getElementsByLocator(Locators.CSS,
                    UILocators.CSS_LOCATOR_MOVIE_DESCRIPTION_ACTORS, element);

            for (WebElement d : data) {
                List<WebElement> starringActors = webController.getElementsByLocator(Locators.CSS,
                        UILocators.CSS_LOCATOR_MOVIE_STARRING_ACTOR, d);
                if (starringActors != null && starringActors.size() != 0) {
                    List<String> actorsList = new ArrayList<String>();
                    for (WebElement starringActor : starringActors) {
                        actorsList.add(starringActor.getText());
                    }
                    movie.setActors(actorsList);
                    movie.setStarringActorsStr(d.getText());
                } else if (!d.getText().equals(movie.getReview())) {
                    movie.setDescription(d.getText());
                }
            }
            movies.add(movie);
        }
        return movies;
    }

    public MainPage clickNext() {
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

    public MainPage clickPrevious() {
        List<WebElement> elements = new ArrayList<WebElement>();
        WebElement mainContainer = webController.getElementByLocator(Locators.CSS,
                UILocators.CSS_LOCATOR_MAIN_CONTAINER);
        List<WebElement> ngScopes = webController.getElementsByLocator(Locators.CSS, UILocators.CSS_LOCATOR_NG_SCOPE,
                mainContainer);
        for (WebElement ngScope : ngScopes) {
            boolean selected = false;
            elements = webController.getElementsByLocator(Locators.CSS, UILocators.CSS_LOCATOR_A_NG_SCOPE, ngScope);
            for (WebElement element : elements) {
                if (element.getText().toLowerCase().equals(PREVIOUS_PAGE_BUTTON)) {
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
