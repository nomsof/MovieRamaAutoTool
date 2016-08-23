package com.workable.movierama.tests;

import java.awt.AWTException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.workable.movierama.controller.WebController;
import com.workable.movierama.pages.MainPage;
import com.workable.movierama.pages.NextPage;
import com.workable.movierama.pages.PreviousPage;
import com.workable.movierama.session.SessionDriver;
import com.workable.movierama.session.constants.BrowserConstants;
import com.workable.movierama.session.constants.BrowserType;
import com.workable.movierama.util.ValidationUtils;

public class MovieRamaTest {
    private static final Logger LOG = LoggerFactory.getLogger(MovieRamaTest.class);

    private MainPage mainPage;
    private NextPage nextPage;
    private PreviousPage previousPage;

    private WebDriver driver;
    private WebController webController;
    private SessionDriver sessionDriver;

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
        nextPage = new NextPage(webController);
        previousPage = new PreviousPage(webController);
        mainPage.returnToMainPage();
    }

    @Test
    public void testMovieRama() throws AWTException, InterruptedException {
        LOG.info("-------------------------------------------------------------------------");
        LOG.info("E2E test scenario running...");
        mainPage.search(randomStringGenerator(5));
        Thread.sleep(BrowserConstants.SLEEP_TIME);
        mainPage.clearSearch();
        Thread.sleep(BrowserConstants.SLEEP_TIME);
        nextPage.open();
        Thread.sleep(BrowserConstants.SLEEP_TIME);
        previousPage.open();
        Thread.sleep(BrowserConstants.SLEEP_TIME);
        mainPage.selectRandomPageNumber();
        Thread.sleep(BrowserConstants.SLEEP_TIME);
        mainPage.selectRandomResultsSize();
        Thread.sleep(BrowserConstants.SLEEP_TIME);
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

    private static String randomStringGenerator(int size) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789_".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

}
