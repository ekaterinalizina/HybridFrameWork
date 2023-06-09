package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialsninja.qa.testBase.TestBase;
import com.tutorialsninja.qa.utilties.Utils;

public class RegisterTest extends TestBase{
	
	public RegisterTest() throws Exception {
		super();
		
	}

	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	
	@BeforeMethod
	public void setup() {
	    driver = openApplication("Chrome");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	
	@Test(priority = 1)
	public void registerAccountWithMandatoryFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Selenium");
		driver.findElement(By.id("input-lastname")).sendKeys("Panda");
		driver.findElement(By.id("input-email")).sendKeys(Utils.emailWithDateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		softassert.assertTrue(driver.findElement(By.xpath("//p[text() = 'Congratulations! Your new account has been successfully created!']")).isDisplayed());
		softassert.assertAll();
	}
	
	@Test(priority = 2)
	public void registerAccountWithExistingEmail() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Selenium");
		driver.findElement(By.id("input-lastname")).sendKeys("Panda");
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: E-Mail Address is already registered!";
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();
	}
	
	@Test(priority = 3)
	public void registerAccountWithAllFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Selenium");
		driver.findElement(By.id("input-lastname")).sendKeys("Panda");
		driver.findElement(By.id("input-email")).sendKeys(Utils.emailWithDateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name = 'newsletter' and @value = '1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		softassert.assertTrue(driver.findElement(By.xpath("//p[text() = 'Congratulations! Your new account has been successfully created!']")).isDisplayed());
		softassert.assertAll();
	}
	
	@Test(priority = 4)
	public void registerAccountWithNoFields() {
		
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualPrivacyPolicyWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
	    String expectedPrivacyPolicyWarningMessage = "Warning: You must agree to the Privacy Policy!";
	    softassert.assertTrue(actualPrivacyPolicyWarningMessage.contains(expectedPrivacyPolicyWarningMessage));
	    
	    String actualFirstNameWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-firstname']/following-sibling::div")).getText();
	    String expectedFirstNameWarningMessage = "First Name must be between 1 and 32 characters!";
	    softassert.assertEquals(actualFirstNameWarningMessage, expectedFirstNameWarningMessage);
	    
	    String actualLastNameWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-lastname']/following-sibling::div")).getText();
	    String expectedLastNameWarningMessage = "Last Name must be between 1 and 32 characters!";
	    softassert.assertEquals(actualLastNameWarningMessage, expectedLastNameWarningMessage);
	     
	    String actualEmailWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-email']/following-sibling::div")).getText();
	    String expectedEmailWarningMessage = "E-Mail Address does not appear to be valid!";
	    softassert.assertEquals(actualEmailWarningMessage, expectedEmailWarningMessage);
	    
	    String actualTelephoneWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-telephone']/following-sibling::div")).getText();
	    String expectedTelephoneWarningMessage = "Telephone must be between 3 and 32 characters!";
	    softassert.assertEquals(actualTelephoneWarningMessage, expectedTelephoneWarningMessage);
	    
	    String actualPasswordWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-password']/following-sibling::div")).getText();
	    String expectedPasswordWarningMessage = "Password must be between 4 and 20 characters!";
	    softassert.assertEquals(actualPasswordWarningMessage, expectedPasswordWarningMessage);
	    
	    softassert.assertAll();
	    
	    
	    
	    
	    
	    
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
