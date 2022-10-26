package ecom_Pack;
//The client wants you to “Verify you are able to change or reorder previously added product”

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

public class Day8 {

	public static WebDriver driver;
	public String firstName = "BERRY";
	public String lastName = "BERRYTWO";

	@BeforeClass
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "G:\\Software\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(Util.baseUrl);
		driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
		// click My Account in footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'][normalize-space()='My Account']"))
				.click();

		// login with registered credentials
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(Util.usernm);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(Util.pswrd);
		driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();
	}

	@Test
	public void verifyReorder() {

		// 4.click reorder+chnge qty+click update
		driver.findElement(By.xpath("//tr[@class='first odd']//a[@class='link-reorder'][normalize-space()='Reorder']"))
				.click();
		WebElement qty = driver.findElement(By.xpath("//input[@title='Qty']"));
		qty.clear();
		qty.sendKeys("5");
		driver.findElement(By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]")).click();

		// 5.verify grand total changed
		System.out.println(driver.findElement(By.cssSelector("strong span[class='price']")).getText());

		// 6.complete billing and shipping information
		Select country = new Select(driver.findElement(By.xpath("//select[@id='country']")));
		country.selectByVisibleText("United States");

		Select state = new Select(driver.findElement(By.xpath("//select[@id='region_id']")));
		state.selectByVisibleText("New York");

		WebElement zip = driver.findElement(By.xpath("//input[@id='postcode']"));
		zip.clear();
		zip.sendKeys("542896");

		// click Estimate
		driver.findElement(By.xpath("//span[contains(text(),'Estimate')]")).click();

		// click radio button Flat rate
		driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']")).click();

		// update total
		driver.findElement(By.xpath("//span[contains(text(),'Update Total')]")).click();
		// fetch grand total
		System.out.println(driver.findElement(By.cssSelector("strong span[class='price']")).getText());

		// Proceed to checkout
		driver.findElement(By.xpath(
				"//li[@class='method-checkout-cart-methods-onepage-bottom']//button[@title='Proceed to Checkout']//span//span[contains(text(),'Proceed to Checkout')]"))
				.click();

		// BILLING INFO-select radio button+continue
		driver.findElement(By.xpath("//label[normalize-space()='Ship to this address']")).click();
		driver.findElement(By.xpath("//button[@onclick='billing.save()']//span//span[contains(text(),'Continue')]"))
				.click();

		// SHIIPING METHOD-Frate displayed +continue
		driver.findElement(
				By.xpath("//button[@onclick='shippingMethod.save()']//span//span[contains(text(),'Continue')]"))
				.click();

		// PAYMENT INFO-select radio button-money order+continue
		driver.findElement(By.xpath("//label[normalize-space()='Check / Money order']")).click();
		driver.findElement(By.xpath("//button[@onclick='payment.save()']//span//span[contains(text(),'Continue')]"))
				.click();

		// click place order
		driver.findElement(By.xpath("//span[contains(text(),'Place Order')]")).click();
		// final page of order number displayed
		System.out.println(
				driver.findElement(By.xpath("//h1[normalize-space()='Your order has been received.']")).getText());
		System.out.println(" Order id: "
				+ driver.findElement(By.xpath("//div[@class='main-container col1-layout']//p[1]/a")).getText());

	}

	@AfterClass
	public void tearDown() {
		// driver.close();
	}
}
