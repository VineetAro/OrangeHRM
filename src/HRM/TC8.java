package HRM;

import org.testng.annotations.Test;

import library_classes.base_lib_OrangeHRM;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class TC8 {
	// Created an object to get Webriver Object value from Base Class

	base_lib_OrangeHRM newOBJ = new base_lib_OrangeHRM();
	WebDriver driver = newOBJ.returnDriver("Chrome");

	WebDriverWait wait = new WebDriverWait(driver, 5);

	// Login and apply for a leave on the HRM site
	@Test
	@Parameters({ "username", "password" })
	public void applyLeave(String username, String password) throws InterruptedException {

// Login to OrangeHRM

		newOBJ.loginOrangeHrm(username, password, driver);
		Thread.sleep(2000);

		// navigate to Leave page
		Actions action = new Actions(driver);
		WebElement leavePage = driver.findElement(By.linkText("Leave"));
		action.moveToElement(leavePage).build().perform();
		WebElement leaveApply = driver.findElement(By.xpath("//*[@id='menu_leave_applyLeave']"));
		action.moveToElement(leaveApply).build().perform();
		leaveApply.click();

		// Verify navigation
		WebElement headTitle = driver.findElement(By.xpath("//*[@id='apply-leave']/div[1]/h1"));
		Assert.assertEquals(headTitle.getText(), "Apply Leave");

		// Apply for leave
		WebElement type = driver.findElement(By.xpath("//select[@id='applyleave_txtLeaveType']"));

		Select leave = new Select(type);
		leave.selectByVisibleText("Paid Leave");

		// Select Leave Date
		WebElement fromDate = driver.findElement(By.id("applyleave_txtFromDate"));
		fromDate.click();

		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		fromDate.sendKeys(date.format(now));

		// Click Apply button
		driver.findElement(By.id("applyBtn")).click();

		// Verify applied leave

		driver.findElement(By.xpath("//*[@id='menu_leave_viewMyLeaveList']")).click();
		WebElement fDate = driver.findElement(By.id("calFromDate"));
		fDate.clear();
		fDate.sendKeys(date.format(now));
		WebElement tDate = driver.findElement(By.id("calToDate"));
		tDate.clear();
		tDate.sendKeys(date.format(now));
		driver.findElement(By.id("btnSearch")).click();

		driver.findElement(By.linkText(date.format(now))).click();
		Thread.sleep(1000);

		// Verify the status
		String status = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[5]")).getText();

		System.out.println("Leave Status is: " + status);

		Reporter.log("Leave successfully applied and s in status: " + status);

	}

	@BeforeMethod
	@Parameters({ "baseURL" })
	public void pageOpen(String baseURL) {
		newOBJ.getpage(driver, baseURL);

	}

	@AfterMethod
	public void closeall() {
		driver.close();
	}

}
