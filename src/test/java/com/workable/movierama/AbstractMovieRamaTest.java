package com.workable.movierama;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.workable.movierama.controller.WebController;
import com.workable.movierama.model.Movie;
import com.workable.movierama.model.pages.MainPage;
import com.workable.movierama.session.SessionDriver;

public abstract class AbstractMovieRamaTest {
    protected MainPage mainPage;

    protected WebDriver driver;
    protected WebController webController;
    protected SessionDriver sessionDriver;

    protected List<Movie> initialMovies;

    @BeforeTest
    public abstract void init();

    @AfterTest
    public abstract void tearDown();
}
