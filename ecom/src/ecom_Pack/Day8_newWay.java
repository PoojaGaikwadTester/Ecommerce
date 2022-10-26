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

public class Day8_newWay extends Day6 {


	Day6 d=new Day6();
/*
	@BeforeClass
	public  void start() {
		
		d.setUp();
	}
*/
	@Test
	public  void verifyReorder() {

		// 4.click reorder+chnge qty+click update
		System.out.println(driver.findElement(By.xpath("//h1[normalize-space()='My Dashboard']")).getText());
		driver.findElement(By.xpath("//tr[@class='first odd']//a[@class='link-reorder'][normalize-space()='Reorder']")).click();
		WebElement qty=driver.findElement(By.xpath("//input[@title='Qty']"));
		qty.clear();
		qty.sendKeys(Util.changeQty);
		driver.findElement(By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]")).click();

		//Repeating same method from Day6.java
		d.verifyCreate();
		//5.verify grand total changed
		//System.out.println("Grand total is : "+driver.findElement(By.cssSelector("strong span[class='price']")).getText());
		System.out.println("Successfull");

		
	    
	}

	@AfterClass
	public void tearDown() {
		// driver.close();
	}
}
