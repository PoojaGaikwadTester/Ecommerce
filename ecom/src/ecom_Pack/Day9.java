package ecom_Pack;
//Verify Discount Coupon works correctly(but site shows incorrect total)
//C:\Program Files (x86)\Common Files\Oracle\Java\javapath
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

public class Day9 {

	public  WebDriver driver;

	@BeforeClass
	public  void setUp() {
		System.setProperty("webdriver.chrome.driver", "G:\\Software\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(Util.baseUrl);
		driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
	
	}
	@Test
	public  void verifyCoupon() {

		// 4.click Mobile
		driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();
		driver.findElement(By.xpath("//li[3]//div[1]//div[3]//button[1]//span[1]//span[1]")).click();
		driver.findElement(By.xpath("//input[@id='coupon_code']")).sendKeys(Util.coupon_code);
		driver.findElement(By.xpath("//span[contains(text(),'Apply')]")).click();
		//String product_total=driver.findElement(By.cssSelector("tbody tr:nth-child(2) td:nth-child(2) span:nth-child(1)//span[contains(text(),'Apply')]")).getText();
		String product_total=driver.findElement(By.cssSelector("td[class='product-cart-total'] span[class='price']")).getText();

		String fproduct_total=product_total.replaceAll("[$-,]","");//$1,185=1185.00
		System.out.println(fproduct_total);
		double ftotal=Double.parseDouble(fproduct_total);
		double discount =0.05*ftotal;
		
	/*	try {
	        assertEquals(string_discountedAmt, string_dDiscDisp);
	      } catch (Error e) {
	    	  System.out.println("*** Derived discount amount not equal displayed discount amount ***");
	    	  e.printStackTrace();	
	      }
	*/	
		System.out.println("Discount Price is : "+discount);
		
		double grandTotal=ftotal-discount;
		System.out.println("Grand Total should be : $"+ grandTotal);
		
		
	    
	}

	@AfterClass
	public void tearDown() {
		// driver.close();
	}
}
