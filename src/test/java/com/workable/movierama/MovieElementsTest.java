package com.workable.movierama;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.workable.movierama.controller.WebController;
import com.workable.movierama.model.Movie;
import com.workable.movierama.model.pages.MainPage;
import com.workable.movierama.session.SessionDriver;
import com.workable.movierama.session.constants.BrowserConstants;
import com.workable.movierama.session.constants.BrowserType;
import com.workable.movierama.util.ValidationUtils;

public class MovieElementsTest {
    private static final Logger LOG = LoggerFactory.getLogger(MovieElementsTest.class);

    private MainPage mainPage;

    private WebDriver driver;
    private WebController webController;
    private SessionDriver sessionDriver;

    private List<Movie> initialMovies;

    @BeforeTest
    public void init() {
        sessionDriver = null;
        try {
            sessionDriver = new SessionDriver(BrowserType.CHROME);
            driver = sessionDriver.getWebDriver();
            webController = new WebController(driver);
        } catch (Exception e) {
            LOG.error("Error while initializing webDriver.", e);
        }
        mainPage = new MainPage(webController);
        mainPage.returnToMainPage();
        initialMovies = mainPage.getMovies();
    }

    @Test
    public void testMovieRama() throws AWTException, InterruptedException {
        LOG.info("-------------------------------------------------------------------------");
        LOG.info("Validating movies in initial page...");
        for (Movie movie : initialMovies) {
            ValidationUtils.validateMovie(movie);
        }
        LOG.info("-------------------------------------------------------------------------");
    }

    @AfterTest
    public void tearDown() {
        try {
            Thread.sleep(BrowserConstants.SLEEP_TIME);
        } catch (Exception e) {
            LOG.error("Error in tearDown", e);
        }
        sessionDriver.quitWebDriver();
    }

}
