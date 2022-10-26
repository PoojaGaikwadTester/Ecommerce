package ecom_Pack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//“Verify that cost of product in list page and details page are equal”.

public class Day2 {

	WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "G:\\Software\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(Util.baseUrl);
		driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);

	}

	@Test
	public void verifyCost() {

		String demoSite = driver.findElement(By.cssSelector("h2")).getText();
		System.out.println(demoSite);
		Assert.assertEquals("THIS IS DEMO SITE FOR   ", demoSite);

		driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();
		System.out.println(driver.getTitle());

		if (driver.findElement(By.xpath("//h2[@class='product-name']/a[@title='Sony Xperia']")).isDisplayed()) {

			String getPricelist = driver.findElement(By.cssSelector("span[id='product-price-1'] span[class='price']")).getText();
			System.out.println(getPricelist);
			driver.findElement(By.xpath("//h2[@class='product-name']/a[@title='Sony Xperia']")).click();
			String getPriceDetail = driver.findElement(By.xpath("//span[@class='price']")).getText();
			
			
			Assert.assertEquals(getPricelist, getPriceDetail, "ListPrice and DetailPrice not matched");
			System.out.println(getPricelist+"equals"+getPriceDetail);
			
		} else {
			System.out.println("Not dispalyed sony xperia");
		}

	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
