package com.tutorialsninja.qa.testBase;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utilties.Utils;

public class TestBase {

	public WebDriver driver;
	public Properties prop;
	public FileInputStream ip;
	
	//Create a Constructor of TestBase
	public TestBase() throws Exception {
		prop = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\java\\com\\tutorialsninja\\qa\\config\\Config.properties");
	    prop.load(ip);
	}

	public WebDriver openApplication(String browserName) {
		if (browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}

		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.IMPLICIT_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.PAGELOAD_TIME));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Utils.SCRIPT_TIME));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		
		return driver;

	}
}
