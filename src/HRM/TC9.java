package HRM;

import org.testng.annotations.Test;

import library_classes.base_lib_OrangeHRM;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class TC9 {
	// Created an object to get Webdriver Object value from Base Class

	base_lib_OrangeHRM newOBJ = new base_lib_OrangeHRM();
	WebDriver driver = newOBJ.returnDriver("Chrome");

	String password;
	String username;

	@BeforeClass
	public void getCredentials() throws IOException {
		// Get username and password
		File passfile = FileUtils.getFile("src/password.txt");
		password = FileUtils.readFileToString(passfile, "UTF8");

		File userfile = FileUtils.getFile("src/username.txt");
		username = FileUtils.readFileToString(userfile, "UTF8");

	}

	// Login and retrieve the emergency contacts for the user

	@Test
	public void getContact() throws  InterruptedException {

// Login using new User

		newOBJ.loginOrangeHrm(username, password, driver);
		Thread.sleep(2000);

// Navigate to MyInfo
		Actions action = new Actions(driver);
		WebElement myInfo = driver.findElement(By.xpath("//*[@id='menu_pim_viewMyDetails']/b"));
		action.moveToElement(myInfo).build().perform();
		myInfo.click();

		// Click on the “Emergency Contacts” menu item

		driver.findElement(By.xpath("//*[@id='sidenav']/li[3]/a")).click();
		driver.findElement(By.id("btnAddContact")).click();

		// Enter Emergency Contact

		driver.findElement(By.id("emgcontacts_name")).sendKeys(username);
		driver.findElement(By.id("emgcontacts_relationship")).sendKeys("Self");
		driver.findElement(By.id("emgcontacts_mobilePhone")).sendKeys("12345678");
		driver.findElement(By.id("btnSaveEContact")).click();

		// Get all Contacts

		List<WebElement> contacts = driver.findElements(By.xpath("//*[@id='emgcontact_list']/tbody"));
		for (WebElement row : contacts) {
			System.out.println(row.getText());
		}

	}

	@BeforeMethod
	@Parameters({ "baseURL" })
	public void openPage(String baseURL) {
		newOBJ.getpage(driver, baseURL);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

}
