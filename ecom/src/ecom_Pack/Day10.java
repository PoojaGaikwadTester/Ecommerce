package ecom_Pack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
/*“Export all Orders in csv file and display these information in console and 
you are able to send this file to another email id as attachment”
id = "user01"
pass = "guru99com"
*/
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Day10 {

	public static WebDriver driver;

	@BeforeClass
	public  void setUp() {
		System.setProperty("webdriver.chrome.driver", "G:\\Software\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(Util.secondUrl);
		driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
		// login with registered credentials
				driver.findElement(By.xpath("//input[@id='username']")).sendKeys(Util.second_usernm);
				driver.findElement(By.xpath("//input[@id='login']")).sendKeys(Util.second_pswrd);
				driver.findElement(By.xpath("//input[@title='Login']")).click();
				for(String handle:driver.getWindowHandles()) {
					driver.switchTo().window(handle);
					driver.findElement(By.xpath("//span[normalize-space()='close']")).click();
				}
					}
	
@Test()
public void exportAll() {
	
	System.out.println("alert closed");
	driver.findElement(By.xpath("//span[normalize-space()='Sales']")).click();
	driver.findElement(By.xpath("//span[normalize-space()='Orders']")).click();
	Select drp=new Select(driver.findElement(By.xpath("//select[@id='sales_order_grid_export']")));
	drp.selectByVisibleText("CSV");
	driver.findElement(By.xpath("//span[contains(text(),'Export')]")).click();

}
@Test(priority=2)
public void readFile() throws IOException {

	File file = new File("C:\\Users\\LENOVO\\Downloads\\orders.csv");
	if(file.exists()){
	 System.out.println("File Exists");
	}
	BufferedReader bufRdr;
	bufRdr = new BufferedReader(new FileReader(file));
	String record; 
	String name,email,pass,cpass= null;

	while ((record = bufRdr.readLine()) != null)
	{
	     String fields[] = record.split(",");
	     name= fields[0].toString();
	     email= fields[1].toString();
	     pass= fields[2].toString();
	     cpass= fields[3].toString();

	     System.out.println(name + "," +email + "," +pass + "," + cpass);
	     
	}
}
	
	@AfterClass
	public void tearDown() {
		// driver.close();
	}
	
}
