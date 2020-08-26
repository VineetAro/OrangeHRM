package library_classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class base_lib_OrangeHRM {
	
	public WebDriver returnDriver(String browser) {
		WebDriver driver = new ChromeDriver();
		if (browser.toUpperCase() == "CHROME") {
			driver = new ChromeDriver();
			return driver;
		}
		else if (browser.toUpperCase() == "FIREFOX") {
			driver = new FirefoxDriver();
			return driver;
	}
		return driver;

}
	public void getpage(WebDriver driver, String baseURL) {
		driver.get(baseURL);
		driver.manage().window().maximize();
	}
	
	public void loginOrangeHrm(String username, String password, WebDriver driver) {
		  //Enter Crednetials
		  driver.findElement(By.id("txtUsername")).sendKeys(username);
		  driver.findElement(By.id("txtPassword")).sendKeys(password);
		  
		  //Click login
		  driver.findElement(By.id("btnLogin")).submit();
		
	}
}
