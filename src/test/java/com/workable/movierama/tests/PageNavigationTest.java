package com.workable.movierama.tests;

import static org.testng.Assert.assertEquals;

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
import com.workable.movierama.pages.MainPage;
import com.workable.movierama.pages.NextPage;
import com.workable.movierama.pages.PreviousPage;
import com.workable.movierama.session.SessionDriver;
import com.workable.movierama.session.constants.BrowserConstants;
import com.workable.movierama.session.constants.BrowserType;

public class PageNavigationTest {
	private static final Logger LOG = LoggerFactory.getLogger(PageNavigationTest.class);

	private List<Movie> movies;

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
	}

	 @Test(priority = 1)
	public void testRandomResultNumberSelection() throws AWTException, InterruptedException {
		LOG.info("-------------------------------------------------------------------------");
		LOG.info("Validating random result number selection...");
		mainPage.returnToMainPage();
		mainPage.selectRandomResultsSize();
		Thread.sleep(BrowserConstants.SLEEP_TIME);

		movies = mainPage.getMovies();
		assertEquals(movies.size(), mainPage.getResultsSizeSelection(),
				"Number of results doesn't respect to user selection.");
	}

	 @Test(priority = 2)
	public void testResultNumberSelection() throws AWTException, InterruptedException {
		LOG.info("-------------------------------------------------------------------------");
		LOG.info("Validating result number selection by using specific number...");
		mainPage.returnToMainPage();
		mainPage.selectResultsSize("50");

		Thread.sleep(BrowserConstants.SLEEP_TIME);

		movies = mainPage.getMovies();
		assertEquals(movies.size(), 50, "Number of results doesn't respect to user selection.");
	}

	@Test(priority = 2)
	public void testPageNumberSelection() throws AWTException, InterruptedException {
		LOG.info("-------------------------------------------------------------------------");
		LOG.info("Validating page number selection...");
		mainPage.selectRandomPageNumber();

		Thread.sleep(BrowserConstants.SLEEP_TIME);

	}

	@Test(priority = 3)
	public void testPageNavigation() throws AWTException, InterruptedException {
		LOG.info("-------------------------------------------------------------------------");
		LOG.info("Validating page navigation...");
		nextPage.open();
		Thread.sleep(BrowserConstants.SLEEP_TIME);
		
		previousPage.open();
		Thread.sleep(BrowserConstants.SLEEP_TIME);
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
