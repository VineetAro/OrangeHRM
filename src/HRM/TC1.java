package HRM;

import org.testng.annotations.Test;

import library_classes.base_lib_OrangeHRM;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class TC1 {
	
	// Created an object to get Webriver Object value from Base Class
	
	base_lib_OrangeHRM newOBJ = new base_lib_OrangeHRM();
	WebDriver driver = newOBJ.returnDriver("Chrome");
	 
  @Test
  public void wTitle() throws InterruptedException {
	// Get the title of the website.
	  String pageTitle = driver.getTitle();
	  
	//Assert “OrangeHRM” with the captured title
	  Assert.assertEquals(pageTitle, "OrangeHRM");
	  Reporter.log("Title caputured successfully. caputured title: " + pageTitle);
	  
  }
  @BeforeMethod
  @Parameters ({"baseURL"})
  public void openBrowser(String baseURL) {
	  newOBJ.getpage(driver, baseURL);
	  
	  // Report if webpage is accessed.  
	  Reporter.log("Webpage opened successfully!! BeforeMETHOD TC1 ");
	  
  }

  @AfterMethod
  public void tearDown() {
	  driver.close();
	  
	  Reporter.log("Webpage closed successfully!! AfterMETHOD TC1 ");
  }

}
