//********************************************************************************************
//*    Guru99 eCommerce Live Project    
//*    Participant : Krishna Rungta                    
//*                                                                                                                                                        *
//********************************************************************************************
/*      
Test Steps:
1. Goto http://live.techpanda.org/
2. Click on ‘MOBILE’ menu
3. In mobile products list , click on ‘Add To Compare’ for 2 mobiles (Sony Xperia & Iphone)
4. Click on ‘COMPARE’ button. A popup window opens
5. Verify the pop-up window and check that the products are reflected in it
   Heading "COMPARE PRODUCTS" with selected products in it.
6. Close the Popup Windows
*/

package ecom_Pack;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
//import static org.junit.Assert.*;
import static org.testng.AssertJUnit.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase4 {
	
	WebDriver driver;
	
	  
	  @BeforeClass
		public void setUp() {
			System.setProperty("webdriver.chrome.driver", "G:\\Software\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(Util.baseUrl);
			driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);

		}
	  
	  @Test
	  public void testTestCase4()  {
		
		
	    
	    // 2. Click on Mobile menu
	    driver.findElement(By.linkText("MOBILE")).click();
	   
	    
	    // 3. In mobile products list , click on ‘Add To Compare’ for 2 mobiles (Iphone & Sony Xperia)
	    
	    //note: store the title of the 2 mobiles for comparison for verification later when popup page comes up
		driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
	    String mainMobile1 = driver.findElement(By.xpath("//h2/a[@title='Samsung Galaxy']")).getText();  // text captured - upperCase "SAMSUNG GALAXY"
	    System.out.println("mainMobile1 = "+mainMobile1);
		driver.findElement(By.xpath("//li[2]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
	    String mainMobile2 = driver.findElement(By.xpath("//h2/a[@title='Sony Xperia']")).getText();  // text captured - upperCase "SONY XPERIA"
	    System.out.println("mainMobile2 = "+mainMobile2);
	    
	    // 4. Click on ‘COMPARE’ button. A popup window opens	   
	    driver.findElement(By.xpath("//button[@title='Compare']")).click();	    
	   
	    
	    // switching to new window
	    for (String handle : driver.getWindowHandles()) {
	    	driver.switchTo().window(handle);
	    	}
	    
	    // 5. Verify the pop-up window and check that the products are reflected in it
	    //    Heading "COMPARE PRODUCTS" with selected products in it.
	    String strHead = ("COMPARE PRODUCTS");
	    String compHead = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div[1]/h1")).getText();	
	    System.out.println("compHead = "+compHead);
	    String popupMobile1 = driver.findElement(By.xpath("//h2/a[@title='Samsung Galaxy']")).getText();  // text captured is "SAMSUNG GALAXY" in uppercase
	    String popupMobile2 = driver.findElement(By.xpath("//h2/a[@title='Sony Xperia']")).getText();  // text captured "SONY XPERIA" in uppercase
	    System.out.println("popupMobile1 = "+popupMobile1);
	    System.out.println("popupMobile2 = "+popupMobile2);
	   
	    // to check the popup heading/title
	    try {	    	
	    	assertEquals(strHead, compHead);
		    } catch (Exception e) {
		    	e.printStackTrace();	    	
		    }	
	    // to check the 2 mobiles selected are the two in the popup - this is to check the IPhone
	    try {	    	
	    	assertEquals(mainMobile1, popupMobile1);
		    } catch (Exception e) {
		    	e.printStackTrace();	    	
		    }	
	    // to check the 2 mobiles selected are the two in the popup - this is to check Sony X
	    try {	    	
	    	assertEquals(mainMobile2, popupMobile2);
		    } catch (Exception e) {
		    	e.printStackTrace();	    	
		    }	
	    	    
	    // 6. Close the Popup Windows
	    driver.findElement(By.xpath("//button[@title='Close Window']")).click();
	    
	    // switching to new window
	    for (String handle : driver.getWindowHandles()) {
	    driver.switchTo().window(handle);
	    }	    
	  }
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(2000);
		driver.quit();
	  }
	
	}
