package HRM;

import org.testng.annotations.Test;

import library_classes.base_lib_OrangeHRM;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class TC3 {
	// Created an object to get Webriver Object value from Base Class
	
			base_lib_OrangeHRM newOBJ = new base_lib_OrangeHRM();
			WebDriver driver = newOBJ.returnDriver("Chrome");
			
			
	
  @Test
  @Parameters({"username", "password"})
  public void login(String username, String password) {
	  newOBJ.loginOrangeHrm(username, password, driver);
	  
	  //Log if the login was successful
	  
	  Reporter.log("Successfully logged in.");
	  
	  //Verify that the homepage has opened.
	  
	  String header = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[1]/h1")).getText();
	  Assert.assertEquals(header, "Dashboard");
	  
	  Reporter.log("Test Completed.");
	  
	  
	  
	  
	  
  }
  
  @BeforeMethod
  @Parameters({"baseURL"})
  public void setUp(String baseURL) {
	  newOBJ.getpage(driver, baseURL);	
	 
	  // Report if webpage is accessed.
	  
	  Reporter.log("Webpage opened successfully!! BeforeMETHOD TC3 ");
  }

  @AfterMethod
  public void tearDown() {
	  driver.close();
  }

}
