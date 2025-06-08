package swaglab_test;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import swaglab_pages.LoginPage;

@Listeners(Listener.class)

public class LoginTest extends BaseClass{
	
	@Test (priority = -1)
	public void LoginSuccessTest() {
		
				
		LoginPage lp =new LoginPage();
		lp.LoginFunction("standard_user", "secret_sauce");
		
		WebElement productTitle = driver.findElement(By.className("title"));
		Assert.assertEquals(productTitle.getText(), "Product");
		
		

	}
	
	@Test (priority = 1)
	public void LoginFailureTest() {
		
		
		LoginPage lp =new LoginPage();
		lp.LoginFunction("standard_user", "secret_sauc");
		
		WebElement ErrorMsg = driver.findElement(By.xpath("//h3[@data-test='error']"));
		Assert.assertEquals(ErrorMsg.getText(), "Epic sadface: Username and password do not match any user in this service");

		
	}

	@Test 

	public void LockedUserLoginFailureTest() {
		
		
		LoginPage lp =new LoginPage();
		lp.LoginFunction("UserNameVal", "PasswordVal");
		
		WebElement ErrorMsg = driver.findElement(By.xpath("//h3[@data-test='error']"));
		Assert.assertEquals(ErrorMsg.getText(), "Epic sadface: Sorry, this user has been locked out.");

	}
	
	@Test 
	public void LoginFailureTestFromExcel() {
		
		String UserNameVal = sheet.getRow(1).getCell(0).getStringCellValue();
		String PasswordVal = sheet.getRow(1).getCell(1).getStringCellValue();
		
		LoginPage lp = new LoginPage();
		lp.LoginFunction(UserNameVal, PasswordVal);
		
		WebElement ErrorMsg = driver.findElement(By.xpath("//h3[@data-test='error']"));
		Assert.assertEquals(ErrorMsg.getText(), "Epic sadface: Username and password do not match any user in this service");
		
		sheet.getRow(1).createCell(2).setCellValue("Completed");
	
	}


@Test
	public void LoginFailureTestFromProperties() throws IOException {

		FileReader reader = new FileReader("data.properties");
		Properties prop = new Properties();
		prop.load(reader);
		
		String UserNameVal = prop.getProperty("username");
		String PasswordVal = prop.getProperty("password");

		LoginPage lp = new LoginPage();
		lp.LoginFunction(UserNameVal, PasswordVal);

		WebElement ErrorMsg = driver.findElement(By.xpath("//h3[@data-test='error']"));
		Assert.assertEquals(ErrorMsg.getText(),
				"Epic sadface: Username and password do not match any user in this service");

		sheet.getRow(1).createCell(2).setCellValue("Completed");

	}



}
