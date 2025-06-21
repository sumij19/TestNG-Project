package swaglab_test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseClass {
	
	public static WebDriver driver;
	
	XSSFWorkbook wbook;
	XSSFSheet sheet;
	
	@BeforeMethod
	
	public void SetUp() throws MalformedURLException {
			
			String BrowserName = System.getProperty("Browser");
			DesiredCapabilities cap = new DesiredCapabilities();
			
			if(BrowserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			}else if(BrowserName.equalsIgnoreCase("firefox_grid")) {
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.WIN11);
				URL HubURL = new URL("http://localhost:4444");
				driver = new RemoteWebDriver(HubURL,cap);
				
			}else if(BrowserName.equalsIgnoreCase("chrome_grid")) {
				
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.WIN11);
				URL HubURL = new URL("http://localhost:4444");
				driver = new RemoteWebDriver(HubURL,cap);
			
			}else {
				driver = new ChromeDriver();
			}
			
			driver.get("https://www.saucedemo.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}

	
	@AfterMethod
	public void TearDown() {
		driver.quit();	
	}
	
	@BeforeTest
	public void SetUpExcel() throws IOException {
		
		FileInputStream fis = new FileInputStream("exceldata.xlsx");
		wbook = new XSSFWorkbook(fis);
		sheet = wbook.getSheet("data");
		
		//else use Fillo  library
		
	}

	@AfterTest
	public void CloseExcel() throws IOException {
		FileOutputStream outputStream = new FileOutputStream("output.xlsx");
		wbook.write(outputStream);
		wbook.close();
	  	 outputStream.close();
	
	}


}
