package com.workable.movierama;

import java.awt.AWTException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.workable.movierama.controller.WebController;
import com.workable.movierama.model.pages.MainPage;
import com.workable.movierama.session.SessionDriver;
import com.workable.movierama.session.constants.BrowserConstants;
import com.workable.movierama.session.constants.BrowserType;

public class MovieRamaTest extends AbstractMovieRamaTest {
    private static final Logger LOG = LoggerFactory.getLogger(MovieRamaTest.class);

    @Override
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
    }

    @Test
    public void testMovieRama() throws AWTException, InterruptedException {
        LOG.info("-------------------------------------------------------------------------");
        LOG.info("E2E test scenario running...");
        mainPage.search(randomStringGenerator(5));
        Thread.sleep(BrowserConstants.SLEEP_TIME);
        mainPage.clearSearch();
        Thread.sleep(BrowserConstants.SLEEP_TIME);
        mainPage.clickNext();
        Thread.sleep(BrowserConstants.SLEEP_TIME);
        mainPage.clickPrevious();
        Thread.sleep(BrowserConstants.SLEEP_TIME);
        mainPage.selectRandomPageNumber();
        Thread.sleep(BrowserConstants.SLEEP_TIME);
        mainPage.selectRandomResultsSize();
        Thread.sleep(BrowserConstants.SLEEP_TIME);
        LOG.info("-------------------------------------------------------------------------");
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

    @Override
    public void tearDown() {
        try {
            Thread.sleep(BrowserConstants.SLEEP_TIME);
        } catch (Exception e) {
            LOG.error("Error in tearDown", e);
        }
        sessionDriver.quitWebDriver();
    }

}
