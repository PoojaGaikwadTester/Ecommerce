package ecom_Pack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//“Verify that you cannot add more product in cart than the product available in store”

public class Day3 {

	WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "G:\\Software\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(Util.baseUrl);
		driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);

	}

	@Test
	public void verifyQty() {

		driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();
		driver.findElement(By.xpath("//li[1]//div[1]//div[3]//button[1]//span[1]//span[1]")).click();
		WebElement enterQty = driver.findElement(By.xpath("//input[@title='Qty']"));
		enterQty.clear();
		enterQty.sendKeys("1000");
		driver.findElement(By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]")).click();
		String errorTxt = driver.findElement(By.xpath("//p[@class='item-msg error']")).getText();
		System.out.println(errorTxt);
		driver.findElement(By.xpath("//span[contains(text(),'Empty Cart')]")).click();
		System.out.println(driver.findElement(By.xpath("//div[@class='cart-empty']/p[1]")).getText());
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
