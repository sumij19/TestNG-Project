package swaglab_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import swaglab_pages.LoginPage;
import swaglab_pages.ProductsPage;

public class ProductTest extends BaseClass{
	
	@Test 
	public void addToCartTest() {
		
				
		LoginPage lp =new LoginPage();
		lp.LoginFunction("standard_user", "secret_sauce");
		
		ProductsPage pp = new ProductsPage();
		pp.AddToCart("Sauce Labs Bolt T-Shirt");
		pp.AddToCart("Sauce Labs Fleece Jacket");
		
		pp.clickIcon();
		
		WebElement productName = driver.findElement(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']"));
		Assert.assertEquals(productName.getText(), "Sauce Labs Bolt T-Shirt");
		
		WebElement productName1 = driver.findElement(By.xpath("//div[text()='Sauce Labs Fleece Jacket']"));
		Assert.assertEquals(productName1.getText(), "Sauce Labs Fleece Jacket");
		
}

}