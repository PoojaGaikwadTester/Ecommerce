package ecom_Pack;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//“Verify that you are able to compare two product

public class Day4 {

	WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "G:\\Software\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(Util.baseUrl);
		driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);

	}

	@Test
	public void verifyCompare() {
		String parent=driver.getWindowHandle();
		Set<String>s=driver.getWindowHandles();
		driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();
		driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
		driver.findElement(By.xpath("//li[2]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
		driver.findElement(By.xpath("//button[@title='Compare']//span//span[contains(text(),'Compare')]")).click();

		Iterator<String> I1= s.iterator();
		while(I1.hasNext())
		{

		String child_window=I1.next();


		if(!parent.equals(child_window))
		{
		driver.switchTo().window(child_window);

		System.out.println(driver.switchTo().window(child_window).getTitle());
		System.out.println(driver.findElement(By.tagName("h1")).getText());
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File(".\\Screnshots\\SS1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,70)");

		driver.findElement(By.xpath("//span[contains(text(),'Close Window')]")).click();
		System.out.println("Successful");

		}
		
		}
		
		
		
		
		
		
	}	

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
