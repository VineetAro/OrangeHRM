package HRM;

import org.testng.annotations.Test;

import library_classes.base_lib_OrangeHRM;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class TC2 {
	// Created an object to get Webriver Object value from Base Class
	
		base_lib_OrangeHRM newOBJ = new base_lib_OrangeHRM();
		WebDriver driver = newOBJ.returnDriver("Chrome");
		
		
  @Test
  // To verify the Url of the Logo in the Login page.
  public void headerLogo() {
	  String logo = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/img")).getAttribute("src");
	 //Print the URL in the login Page to the console.
	  
	  System.out.println("Logo URL is: "+ logo );
	  
	 // Log report
	  
	  Reporter.log("Logo successfully identified: " + logo);
	  
	  
  }
  @BeforeMethod
  @Parameters({"baseURL"})
  public void beforeMethod(String baseURL) {
	  newOBJ.getpage(driver, baseURL);	 
	  // Report if webpage is accessed.
	  
	  Reporter.log("Webpage opened successfully!! BeforeMETHOD TC2 ");
  }

  @AfterMethod
  public void tearDown() {
	  driver.close(); 
  }

}
