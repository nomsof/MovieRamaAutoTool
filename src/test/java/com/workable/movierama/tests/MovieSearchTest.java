package com.workable.movierama.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.workable.movierama.controller.WebController;
import com.workable.movierama.model.Movie;
import com.workable.movierama.pages.MainPage;
import com.workable.movierama.session.SessionDriver;
import com.workable.movierama.session.constants.BrowserConstants;
import com.workable.movierama.session.constants.BrowserType;
import com.workable.movierama.util.ValidationUtils;

public class MovieSearchTest {
	private static final Logger LOG = LoggerFactory.getLogger(MovieSearchTest.class);

	private static final String SEARCH_WITH_NO_RESULTS = "RESULTS_MUST_NOT_бе_FOUND";

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
		initialMovies = mainPage.getMovies();
	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "Keyword search.", new String[] { "pets" } },
				{ "Search by using 2 keywords.", new String[] { "pets", "the" } },
				{ "No results found.", new String[] { SEARCH_WITH_NO_RESULTS } },
				{ "User can clear search results and see the first page.", new String[] {} } };
	}

	@Test(dataProvider = "getData", enabled = false)
	public void testMovieRama(String testName, String[] keywords) throws AWTException, InterruptedException {
		LOG.info("-------------------------------------------------------------------------");
		LOG.info("Test running \"{}\"...", testName);
		mainPage.returnToMainPage();

		if (keywords.length > 0) {
			mainPage.search(createKeyWord(keywords));
		} else {
			mainPage.clearSearch();
		}
		Thread.sleep(BrowserConstants.SLEEP_TIME);
		List<Movie> movies = mainPage.getMovies();

		// Results were not found
		if (movies.size() == 0) {
			List<WebElement> elements = mainPage.getSystemResponse();
			assertEquals(elements.size(), 1,
					"No movie was found satisfying search criterion and that was not communicated to the user. ");
			assertNotNull(elements.get(0).getText(),
					"No movie was found satisfying search criterion and that was not communicated to the user. ");
			LOG.info("System answer: {}", elements.get(0).getText());
		} else {
			// Results were found
			if (keywords.length > 0) {
				for (Movie movie : movies) {
					ValidationUtils.validateMovie(movie);
					for (String keyword : keywords) {
						if (!movie.getTitle().toLowerCase().contains(keyword)) {
							throw new AssertionError("Movie " + movie + " doesn't contain keyword " + keyword + ".");
						}
					}
				}
			} else {
				// The search box is clear
				if (!validateInitialMovies(movies)) {
					throw new AssertionError("Movies returned are not like the previous results.");
				}
			}
		}

	}

	private String createKeyWord(String[] keywords) {
		StringBuilder sb = new StringBuilder();
		for (String word : keywords) {
			sb.append(word + " ");
		}
		return sb.toString();
	}

	private boolean validateInitialMovies(List<Movie> movies) {
		for (Movie initialMovie : initialMovies) {
			boolean found = false;
			for (Movie movie : movies) {
				if (initialMovie.getTitle().equals(movie.getTitle())) {
					found = true;
					break;
				}
			}
			if (!found) {
				return false;
			}
		}
		return true;
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
