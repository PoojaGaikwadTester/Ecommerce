package ecom_Pack;
//Verify user is able to purchase product-LG LCD  using registered credentialsS
//same pkg,diff class-private
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

public class Day6 {

	public  WebDriver driver;
	public String firstName = "BERRY";
	public String lastName = "BERRYTWO";

	@BeforeClass
	public  void setUp() {
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

	@Test(priority=0)
	//private bcoz i dont want this method in Day8_newWay.java=not accessible there
	protected  void addToCart() {    
		// click wishlist
		
		System.out.println(driver.findElement(By.xpath("//div[@class='block-content']//a[normalize-space()='My Wishlist']")).getText());
		driver.findElement(By.xpath("//div[@class='block-content']//a[normalize-space()='My Wishlist']")).click();
		
		//click add to cart
		driver.findElement(By.xpath("//button[@title='Add to Cart']")).click();
	}
	
	@Test(priority=1)
	public  void verifyCreate() {
		
		//select country,state,zip
		Select country = new Select(driver.findElement(By.xpath("//select[@id='country']")));
		country.selectByVisibleText("United States");

		Select state = new Select(driver.findElement(By.xpath("//select[@id='region_id']")));
		state.selectByVisibleText("New York");
		
		WebElement zip = driver.findElement(By.xpath("//input[@id='postcode']"));
		zip.clear();
		zip.sendKeys("542896");
		
		//click Estimate
		driver.findElement(By.xpath("//span[contains(text(),'Estimate')]")).click();
		
		//click radio button Flat rate
		driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']")).click();

		//update total
		driver.findElement(By.xpath("//span[contains(text(),'Update Total')]")).click();

		
		//Proceed to checkout
		String Frate = driver.findElement(By.cssSelector("label[for='s_method_flatrate_flatrate'] span[class='price']")).getText();//Frate=5%
		System.out.println("---Shipping Flat rate is : ---" + Frate.replaceAll("[$]",""));
/*		String subtotal = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(2) > span:nth-child(1)")).getText();
		System.out.println(subtotal);
		int total1 = Integer.parseInt(subtotal) + Integer.parseInt(Frate);
		String total=Integer.toString(total1);
*/		
		
		String displayed_total =driver.findElement(By.cssSelector("strong span[class='price']")).getText();
		
/*		System.out.println(total + "====" + displayed_total);
		Assert.assertEquals(total, displayed_total);
*/		
		driver.findElement(By.xpath("//li[@class='method-checkout-cart-methods-onepage-bottom']//button[@title='Proceed to Checkout']//span//span[contains(text(),'Proceed to Checkout')]")).click();
		
		//BILLING INFO-select radio button+continue
		driver.findElement(By.xpath("//label[normalize-space()='Ship to this address']")).click();
		driver.findElement(By.xpath("//button[@onclick='billing.save()']//span//span[contains(text(),'Continue')]")).click();
		
		//SHIIPING METHOD-Frate displayed +continue
		String flatRate_final=driver.findElement(By.cssSelector("label[for='s_method_flatrate_flatrate'] span[class='price']")).getText();
//Assert.assertEquals(Frate, flatRate_final);
		driver.findElement(By.xpath("//button[@onclick='shippingMethod.save()']//span//span[contains(text(),'Continue')]")).click();

		
		//PAYMENT INFO-select radio button-money order+continue
		driver.findElement(By.xpath("//label[normalize-space()='Check / Money order']")).click();
		driver.findElement(By.xpath("//button[@onclick='payment.save()']//span//span[contains(text(),'Continue')]")).click();

		//ORDER REVIEW- window
		String total_displayed=driver.findElement(By.cssSelector("strong span[class='price']")).getText();
//Assert.assertEquals(displayed_total, total_displayed);

		//click place order
		driver.findElement(By.xpath("//span[contains(text(),'Place Order')]")).click();

	/*	
		driver.findElement(By.xpath(
				"//ul[@class='checkout-types top']//li//button[@title='Proceed to Checkout']//span//span[contains(text(),'Proceed to Checkout')]"))
				.click();

		WebElement address = driver.findElement(By.xpath("//input[@id='billing:street1']"));
		address.clear();
		address.sendKeys("ABC,new york,USA");
*/
		//WebElement city = driver.findElement(By.xpath("//input[@id='billing:city']"));
		//city.clear();
		//city.sendKeys("New york");

		//Select state = new Select(driver.findElement(By.xpath("//select[@id='billing:region_id']")));
		//state.selectByVisibleText("New York");

		//WebElement zip = driver.findElement(By.xpath("//input[@id='billing:postcode']"));
		//zip.clear();
		//zip.sendKeys("542896");

		//Select country = new Select(driver.findElement(By.xpath("//select[@id='billing:country_id']")));
		//country.selectByVisibleText("United States");

/*		WebElement telephone = driver.findElement(By.xpath("//input[@id='billing:telephone']"));
		telephone.clear();
		telephone.sendKeys("12345678");
*/
	/*	driver.findElement(By.xpath("//label[@for='billing:use_for_shipping_yes']")).click();
		
		driver.findElement(By.xpath("//button[@onclick='shipping.save()']//span//span[contains(text(),'Continue')]"))
				.click();
*/
		//String Frate = driver.findElement(By.xpath("//span[normalize-space()='$5.00']")).getText();
		//System.out.println("Shipping Flat rate is : " + Frate);
/*		driver.findElement(
				By.xpath("//button[@onclick='shippingMethod.save()']//span//span[contains(text(),'Continue')]"))
				.click();

		driver.findElement(By.xpath("//label[@for='p_method_checkmo']")).click();
		driver.findElement(By.xpath("//button[@class='button']//span//span[contains(text(),'Continue')]")).click();
*/
		//String subtotal = driver.findElement(By.cssSelector("tr[class='first'] span[class='price']")).getText();
		//String total = subtotal + Frate;
		//String displayed_total = driver.findElement(By.xpath("strong span[class='price']")).getText();
		//System.out.println(total + "" + displayed_total);
		//Assert.assertEquals(total, displayed_total);
		//driver.findElement(By.xpath("//span[contains(text(),'Place Order')]")).click();

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
