package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {
	
	
	@Test 
	public void incorrectUsernameTest() {
		System.out.println("Starting");
		
		//set the browser
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//open test page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page it's opened");
		
		driver.manage().window().maximize();
		
		//enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("IncorrectUserName");
		
		//enter password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");
		
		//click login button
		By buttonType = By.tagName("Button");
		WebElement loginbutton = driver.findElement(buttonType);
		loginbutton.click();
		
		//verification:
		//new url
		String ExpectedURL = "http://the-internet.herokuapp.com/login";
		String ActualURL = driver.getCurrentUrl();
		Assert.assertEquals(ActualURL, ExpectedURL,"Actual URL is not equal like expected URL.");
		
		//verifications error message for username
		WebElement ErrorMessage1 = driver.findElement(By.id("flash"));
		String ExpectedErrorMessage = "Your username is invalid!";
		String ActualErrorMessage = ErrorMessage1.getText();
		Assert.assertTrue(ActualErrorMessage.contains(ExpectedErrorMessage),"Actual message don't contain the expected message. \nActual message: " + ActualErrorMessage + "\nExpected message: " + ExpectedErrorMessage);
		
		driver.quit();
	}

	@Test
	public void incorrectPasswordTest() {
		
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("open the page");
		
		driver.manage().window().maximize();
		
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("incorrectPassword");
		
		WebElement logInButton = driver.findElement(By.tagName("button"));
		logInButton.click();
		
		WebElement ErrorMessage2 = driver.findElement(By.id("flash"));
		String ExpectedErrorMessage2 = "Your password is invalid!";
		String ActuralErrorMessage2 = ErrorMessage2.getText();
		Assert.assertTrue(ActuralErrorMessage2.contains(ActuralErrorMessage2),"Actual message don't contain the expected message. /nActual message: "+ ActuralErrorMessage2 + "\nExpected Message" + ExpectedErrorMessage2);
		
		driver.quit();
		
		
	}
}
