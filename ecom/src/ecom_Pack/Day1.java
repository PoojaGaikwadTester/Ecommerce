package ecom_Pack;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Day1 {

	WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "G:\\Software\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(Util.baseUrl);
		driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);

	}

	@Test
	public void verify() {

		String demoSite = driver.findElement(By.cssSelector("h2")).getText();
		System.out.println(demoSite);
		Assert.assertEquals("THIS IS DEMO SITE FOR   ", demoSite);

		driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();
		System.out.println(driver.getTitle());
		Select drpdwn = new Select(driver.findElement(By.tagName("select")));
		drpdwn.selectByVisibleText("Name");

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		Random random = new Random();
		try {
			FileUtils.copyFile(source, new File(".\\Screnshots\\" + random.nextInt() + ".png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
