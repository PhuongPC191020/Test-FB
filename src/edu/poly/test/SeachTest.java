package edu.poly.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeachTest {
	@Test
	public void testSeach() {
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://lms.poly.edu.vn");
		
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("Fpoly DaNang");
		
		driver.findElement(By.name("btnK")).click();
	
	}
}
