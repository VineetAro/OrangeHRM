package HRM;

import org.testng.annotations.Test;

import library_classes.base_lib_OrangeHRM;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class TC4 {
	// Created an object to get Webriver Object value from Base Class

	base_lib_OrangeHRM newOBJ = new base_lib_OrangeHRM();
	WebDriver driver = newOBJ.returnDriver("Chrome");

	WebDriverWait wait = new WebDriverWait(driver, 20);

	@Test
	@Parameters({ "username", "password" })
	public void newEmployee(String username, String password) throws IOException, InterruptedException {
		newOBJ.loginOrangeHrm(username, password, driver);

		// Find the PIM option in the menu and click it.

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector("#menu_pim_viewPimModule > b"))).build().perform();
		driver.findElement(By.xpath("//*[@id='menu_pim_addEmployee']")).click();

		// Create a random number for first and Last name to add as suffix
		Random suffix = new Random();

		// Enter new employee details and save the record.
		String firstname = "Firstname_" + suffix.nextInt(1000);
		String lastname = "LastName_" + suffix.nextInt(1000);

		driver.findElement(By.id("firstName")).sendKeys(firstname);
		driver.findElement(By.id("lastName")).sendKeys(lastname);

		// Create Credentials
		driver.findElement(By.id("chkLogin")).click();

		String nUsername = "VineetArora" + suffix.nextInt(200);
		String nPassword = "VineetArora" + suffix.nextInt(200);

		driver.findElement(By.id("user_name")).sendKeys(nUsername);
		driver.findElement(By.id("user_password")).sendKeys(nPassword);
		driver.findElement(By.id("re_password")).sendKeys(nPassword);

		driver.findElement(By.id("btnSave")).click();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Save new Username and password
		File file = new File("src/username.txt");
		file.delete();
		file.createNewFile();
		FileWriter writer_username = new FileWriter("src/username.txt");
		writer_username.write(nUsername);
		writer_username.close();

		File file1 = new File("src/password.txt");
		file1.delete();
		file1.createNewFile();
		FileWriter writer_password = new FileWriter("src/password.txt");
		writer_password.write(nPassword);
		writer_password.close();

		// Verify the profile in Admin View.

		// Navigate to Admin View
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement admin = driver.findElement(By.cssSelector("#menu_admin_viewAdminModule > b"));
		action.moveToElement(admin).doubleClick().perform();

		driver.findElement(By.xpath("//*[@id='menu_admin_viewAdminModule']")).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).sendKeys(nUsername);
		driver.findElement(By.xpath("//input[@id='searchBtn']")).click();

		WebElement verify_user = driver.findElement(By.linkText(nUsername));
		Assert.assertEquals(verify_user.getText(), nUsername);

		// Report Logging

		Reporter.log("Employee record successfully created");

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
