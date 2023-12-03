package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

	
	@Test  //test annotation
	public void loginTest() {
		System.out.println("Starting ");

		// Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// sleep for 3 seconds
		sleep(3000);
		

		// open test page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is opened.");

		

		// maximaze.browser window
		driver.manage().window().maximize();

		
		// enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");		
				sleep(1000);
				
		
		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
				sleep(3000);
		
				
		// click login button
       WebElement logInButton = driver.findElement(By.tagName("button"));
       logInButton.click();
		sleep(3000);

		
		
		// verification:
		// new url
		String expectedUrl = "http://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl,"Actual page url is not the same as expected");
		 
		
		// logout button is visible
       WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
       Assert.assertTrue(logOutButton.isDisplayed(), "Log Out button is not visible");
       
       
		// succesful login message
	   //WebElement successMessage = driver.findElement(By.cssSelector("#flash"));	
       WebElement successMessage = driver.findElement(By.xpath("//div[@id='flash']"));
       String expectedMessage = "You logged into a secure area!";
       String actualMessage = successMessage.getText();
       //Assert.assertEquals(actualMessage, expectedMessage,"Actual message is no the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage),"Actual message does not contain expected message.\nActual Message: "+ actualMessage + "\nexpected Message :" + expectedMessage);
		
		
		//close browser
		driver.quit();
	}
	
	

	// method that is refactor for Sleep and avoid have more lines
	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
