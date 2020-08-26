package HRM;

import org.testng.annotations.Test;
import library_classes.base_lib_OrangeHRM;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class TC6 {
	
// Created an object to get Webriver Object value from Base Class

	base_lib_OrangeHRM newOBJ = new base_lib_OrangeHRM();
	WebDriver driver = newOBJ.returnDriver("Chrome");
	
	WebDriverWait wait = new WebDriverWait(driver, 5);
	

  @Test
  public void verifyDirectory() throws IOException {
	// Get username and password
		 File passfile = FileUtils.getFile("src/password.txt");
		 String password =  FileUtils.readFileToString(passfile, "UTF8");
		 
		 File userfile = FileUtils.getFile("src/username.txt");
		 String username =  FileUtils.readFileToString(userfile, "UTF8");
		 
	// Login using new User
		 
		 newOBJ.loginOrangeHrm(username, password, driver);
		 
	// Navigate to Directory 
		 WebElement directory = driver.findElement(By.partialLinkText("Directory"));
		 Actions action = new Actions(driver);
		 action.moveToElement(directory).build().perform();
		 wait.until(ExpectedConditions.visibilityOf(directory));
		 directory.click();


	//Get the Webelement 
	// Wait till element is visible.
	//Verify Directory
		 
		 WebElement directory_menu = driver.findElement(By.xpath("//*[@id='content']/div[1]/div[1]/h1"));
		 wait.until(ExpectedConditions.visibilityOf(directory_menu));
		 String directory_text = directory_menu.getText();
		 Assert.assertEquals(directory_text, "Search Directory");
		 
		 Reporter.log("TC6: Navigation to 'Directory' Menu Item was successful.");
  }
  @BeforeMethod
  @Parameters ({"baseURL"})
  public void pageOpen(String baseURL) {
	  
	  newOBJ.getpage(driver, baseURL);
  }

  @AfterMethod
  public void tearDown() {
	  driver.close();
  }

}
