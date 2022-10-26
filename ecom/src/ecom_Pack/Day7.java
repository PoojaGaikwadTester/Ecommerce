package ecom_Pack;
//Verify that you will be able to save previously placed order as a pdf file

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Day7 {

	WebDriver driver;
	public String firstName = "BERRY";
	public String lastName = "BERRYTWO";

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "G:\\Software\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(Util.baseUrl);
		driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);

	}

	@Test
	public void verifyCreate() {
		// 2.click My Account in footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'][normalize-space()='My Account']"))
				.click();

		// 3.login with registered credentials
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(Util.usernm);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(Util.pswrd);
		driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();
		
		// 4.click My Orders
		System.out.println(driver.findElement(By.xpath("//a[normalize-space()='My Orders']")).getText());
		driver.findElement(By.xpath("//a[normalize-space()='My Orders']")).click();
		
		//5.verify previously created order displayed in RECENT ORDERS
		System.out.println(driver.findElement(By.cssSelector("tr[class='first odd'] td[class='number']")).getText());//order number
		System.out.println(driver.findElement(By.xpath("//tr[@class='first odd']//em[contains(text(),'Pending')]")).getText());//status-pending
		driver.findElement(By.xpath("//tr[@class='first odd']//a[contains(text(),'View Order')]")).click();

		//6.click view order
		driver.findElement(By.xpath("//a[normalize-space()='Print Order']")).click();

		//7.see if dwnloaded as pdf------------not executed.
		for(String handle:driver.getWindowHandles()){
			driver.switchTo().window(handle);
		}
		
		int size = driver.findElements(By.tagName("iframe")).size();//0th iframe
		System.out.println(size);
		driver.switchTo().frame(0);
	    //driver.findElement(By.linkText("Print Order")).click();
		Select sPdf = new Select(driver.findElement(By.xpath("//select[@class='md-select']")));
		sPdf.selectByVisibleText("Save as PDF");
	    driver.findElement(By.linkText("Save")).click();

	    
	}

	@AfterClass
	public void tearDown() {
		// driver.close();
	}
}
