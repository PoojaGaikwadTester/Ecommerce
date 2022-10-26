package ecom_Pack;
import static org.testng.Assert.assertEquals;

//Verify you can create account in E-commerce site and can share wishlist to other people using email
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Day5 {

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
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'][normalize-space()='My Account']")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account'] span span")).click();

		WebElement first=driver.findElement(By.cssSelector("#firstname"));
		first.clear();
	first.sendKeys(firstName);
	
	WebElement middle=driver.findElement(By.cssSelector("#middlename"));
	middle.clear();
	middle.sendKeys("mmm");
	
	WebElement last=driver.findElement(By.cssSelector("#lastname"));
	last.clear();
	last.sendKeys(lastName);

	WebElement email=driver.findElement(By.cssSelector("#email_address"));
	email.clear();
	email.sendKeys(Util.usernm);
	
	
	WebElement password=driver.findElement(By.cssSelector("#password"));
	password.clear();
	password.sendKeys(Util.pswrd);
	
	WebElement cpassword=driver.findElement(By.cssSelector("#confirmation"));
	cpassword.clear();
	cpassword.sendKeys("r3VCDKNgwEqgijP");
	
	driver.findElement(By.cssSelector("button[title='Register'] span span")).click();
	System.out.println("Account Registration Done");
	
	String vWelcome = ("WELCOME, " + first + " " + last + "!");
    String tWelcome = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[1]/div/p")).getText();
    System.out.println("tWelcome = "+tWelcome);
   /* try {	    	
    	assertEquals(tWelcome, vWelcome);
	    } catch (Exception e) {
	    	e.printStackTrace();	    	
	    }		
*/
    System.out.println(driver.findElement(By.xpath("//h1[normalize-space()='My Dashboard']")).getText());
    driver.findElement(By.xpath("//a[normalize-space()='TV']")).click();
    System.out.println(driver.findElement(By.xpath("//h1[normalize-space()='TV']")).getText());
	    driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[1]/div[1]/div[3]/ul[1]/li[1]/a[1]")).click();
	   driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();

		driver.findElement(By.xpath("//textarea[@id='email_address']")).sendKeys("cindy860038@gmail.com,automatione12@gmail.com");
		driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Hi,Please acknowledge.");
		driver.findElement(By.xpath("//span[contains(text(),'Share Wishlist')]")).click();
		
		String wishsharedmsg=driver.findElement(By.xpath("//span[normalize-space()='Your Wishlist has been shared.']")).getText();
try {	    	
	assertEquals(wishsharedmsg, "Your Wishlist has been shared.", "Something went wrong");
    } catch (Exception e) {
    	e.printStackTrace();	    	
    }		System.out.println("Wishlist Shared Successfully");

	
	
	}
	
	
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
