package HRM;

import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;

import library_classes.base_lib_OrangeHRM;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class TC5 {
	// Created an object to get Webriver Object value from Base Class

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

	@Test
	public void userInfo() throws IOException {

		// Login to the application.
		newOBJ.loginOrangeHrm(username, password, driver);

		// Find the “My Info” menu item and click it.
		Actions action = new Actions(driver);
		WebElement myInfo = driver.findElement(By.xpath("//*[@id='menu_pim_viewMyDetails']/b"));
		action.moveToElement(myInfo).build().perform();
		myInfo.click();

		// Edit the Details
		driver.findElement(By.xpath("//*[@id='btnSave']")).click();

		Random suffix = new Random();
		int year = 1900 + suffix.nextInt(99);

		// Edit the DOB.
		/*
		 * WebElement dob = driver.findElement(By.xpath("//*[@id='personal_DOB']"));
		 * dob.clear(); dob.sendKeys(year+"-01-31");
		 */
		// Edit Gender Male
		driver.findElement(By.xpath("//*[@id='personal_optGender_1']")).click();

		// Edit Name
		WebElement firstname = driver.findElement(By.xpath("//*[@id='personal_txtEmpFirstName']"));
		WebElement lastname = driver.findElement(By.xpath("//*[@id=\"personal_txtEmpLastName\"]"));
		// Clear name values
		firstname.clear();
		lastname.clear();

		// Enter new values
		firstname.sendKeys("Vineet" + year);
		lastname.sendKeys("Arora" + year);

		// Add Status
		Select status = new Select(driver.findElement(By.id("personal_cmbMarital")));
		status.selectByValue("Single");

		// Select Nationality
		Select national = new Select(driver.findElement(By.id("personal_cmbNation")));
		national.selectByVisibleText("Indian");

		// Save teh record.
		driver.findElement(By.id("btnSave")).click();

		Reporter.log("Employee record successfully Updated");

	}

	@BeforeMethod
	@Parameters({ "baseURL" })
	public void pageOpen(String baseURL) {

		newOBJ.getpage(driver, baseURL);

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
