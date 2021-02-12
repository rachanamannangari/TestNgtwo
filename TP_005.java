package twoplug;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TP_005 {
	public WebDriver driver;
	public String url = "https://qatest.twoplugs.com";

	@BeforeClass
	//@Parameters("browser")//cross browser testing
	public void browserlunch()
	{
		//if(browser.equalsIgnoreCase("ff"))
		//{
			System.setProperty("webdriver.gecko.driver","C:\\selenium\\geckodriver.exe");
			driver=new FirefoxDriver();
			
		//}
		//else if(browser.equalsIgnoreCase("chrome")){
		//	System.setProperty("webdriver.chrome.driver","C:\\selenium\\chromedriver.exe");
			//driver=new ChromeDriver();
			
			
		//}

	}


	@BeforeMethod
	public void login() throws InterruptedException {
		driver.get(url);
		driver.findElement(By.xpath(
				"//body[@class='main-template']/div[@class='wrapper']/header/div[@class='container']/ul[@class='control-bar']/li[1]/a[1]"))
				.click();
		driver.findElement(By.xpath("//input[@name='email']")).clear();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("main");
		;
		driver.findElement(By.xpath("//input[@id='signInPassword']")).clear();
		driver.findElement(By.xpath("//input[@id='signInPassword']")).sendKeys("test2plug");
		driver.findElement(By.xpath("//ul[@class='line-btn']//button[@class='btn btn-success w-btn-success']")).click();
		Thread.sleep(5000);
		String act = driver.findElement(By.xpath("//div[@class='name']")).getText();// Assertion for user "Sun"
		Assert.assertTrue(act.contains("Main"));

	}

	@Test
	public void searchUser() throws InterruptedException {
		WebElement search = driver.findElement(By.xpath("//input[@name='q']"));
		search.sendKeys("moon");// Searching for the user moon
		search.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		WebElement user = driver.findElement(By.xpath("//table[@class='result-table'] /tbody"));// Assign all
																											// search
				int count=user.findElements(By.tagName("tr")).size();																						// elements
																											// in to
																											// List
		//int count = user.size();// total number of search result

		System.out.println("number is" + count);
		for(int i=0;i<=count;i++) {
			Thread.sleep(5000);
			driver.navigate().refresh();
			//WebDriverWait wait = new WebDriverWait(driver,30);
			//wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(user)));
			//user.get(i).findElement(By.xpath("//a[@class='img']")).click();// Click on
			user.findElements(By.tagName("tr")).get(i).findElement(By.xpath("//a[@class='img']")).click();														// element
					Thread.sleep(5000);																						// in the
																											// search
																											// result
			String text = driver.findElement(By.xpath("//tr/td/a")).getText();// navigate to
																										// corresponding
																										// user page
			if (text.equalsIgnoreCase("moon"))// Verifying whether user name is moon
			{
				Assert.assertTrue(text.contains("Moon"));// Add assertion if its true exit the loop
				break;
			} 
			else {
				driver.navigate().back();
				
				//WebDriverWait wait = new WebDriverWait(driver,30);
				//wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(user)));
				// if user name is not moon navigate back to search result page
			
			}
			//wait_until(lambda: is_element_stale(old_link_reference))
			//WebDriverWait wait = new WebDriverWait(driver,30);
	     //WebElement text2  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='result-table'] /tbody")));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='result-table'] /tbody")));
		//wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(user)));
		
		}
	
	}
	
	@AfterClass
	public void browserClose()
	{
		driver.close();
	}
}
