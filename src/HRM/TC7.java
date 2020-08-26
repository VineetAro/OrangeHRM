package HRM;

import org.testng.annotations.Test;

import library_classes.base_lib_OrangeHRM;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class TC7 {
	// Created an object to get Webriver Object value from Base Class

	base_lib_OrangeHRM newOBJ = new base_lib_OrangeHRM();
	WebDriver driver = newOBJ.returnDriver("Chrome");

	WebDriverWait wait = new WebDriverWait(driver, 5);
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

	// Add employee qualifications
	@Test
	public void qualification() throws InterruptedException {

		// Login using new User

		newOBJ.loginOrangeHrm(username, password, driver);
		Thread.sleep(2000);

		// Navigate to MyInfo
		Actions action = new Actions(driver);
		WebElement myInfo = driver.findElement(By.xpath("//*[@id='menu_pim_viewMyDetails']/b"));
		action.moveToElement(myInfo).build().perform();
		myInfo.click();
		// Add Qualification
		driver.findElement(By.xpath("//*[@id='sidenav']/li[9]/a")).click();
		driver.findElement(By.id("addWorkExperience")).click();
		driver.findElement(By.id("experience_employer")).sendKeys("IBM India");
		driver.findElement(By.id("experience_jobtitle")).sendKeys("CRM Consultant");

		WebElement date = driver.findElement(By.id("experience_from_date"));
		date.clear();
		date.sendKeys("2011-07-11");
		driver.findElement(By.id("btnWorkExpSave")).click();
		// Verify record

		List<WebElement> qualificationList = driver
				.findElements(By.xpath("//*[@id='frmDelWorkExperience']/table/tbody//*[@class='name']/a"));
		for (WebElement name : qualificationList) {
			String sName = name.getText();
			System.out.println(sName);
			if (sName.equals("IBM India")) {
				Reporter.log("Qualification Successfully created");
				break;
			}

		}
	}

	@BeforeTest
	@Parameters({ "baseURL" })
	public void openPage(String baseURL) {
		newOBJ.getpage(driver, baseURL);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
