package com.workable.movierama.session;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.workable.movierama.session.constants.BrowserConstants;
import com.workable.movierama.session.constants.BrowserType;

public class SessionDriver {
	private WebDriver webDriver;

	public SessionDriver(BrowserType browserType) throws UnsupportedEncodingException {
		switch (browserType) {
		case CHROME:
			setSystemPropertiesForDriver(BrowserConstants.CHROME_DRIVER_EXE_PATH, BrowserConstants.CHROME_DRIVER_EXE);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			webDriver = new ChromeDriver(cap);
			break;
		case FIREFOX:
			break;
		default:
			break;
		}
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
	}

	public void closeWebDriver() {
		webDriver.close();
	}

	public void quitWebDriver() {
		webDriver.quit();
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	private void setSystemPropertiesForDriver(String driverExePath, String driverExe)
			throws UnsupportedEncodingException {
		System.setProperty(driverExePath,
				URLDecoder.decode(getClass().getClassLoader().getResource(".").getPath(), "UTF-8")
						+ System.getProperty("file.separator") + BrowserConstants.DRIVERS_FOLDER
						+ System.getProperty("file.separator") + driverExe);
	}
}
