package swaglab_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swaglab_test.BaseClass;

public class ProductsPage {
	
	WebDriver driver = BaseClass.driver;
	
	//=====================================Locators===========================================
	@FindBy(xpath = "//a[@data-test='shopping-cart-link']")
	WebElement cartIcon;
 
		
	//=====================================Methods============================================

		public ProductsPage() {
			
			PageFactory.initElements(driver, this);
			
		}
		
		public void AddToCart(String productName) {

			String productXpath = "//div[text()= '" + productName + "']//following::button[1]";
					
			WebElement addToCart = driver.findElement(By.xpath(productXpath));
			addToCart.click();
			//cartIcon.click();
			
		}
		
		public void clickIcon() {
			cartIcon.click();
		}

}
