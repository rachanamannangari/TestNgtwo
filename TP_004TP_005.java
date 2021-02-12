package twoplug;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TP_004TP_005 {
	public WebDriver driver;
	public String url = "https://qatest.twoplugs.com";
	@BeforeClass
	@Parameters("browser")//cross browser testing
	public void browserlunch(String browser)
	{
		if(browser.equalsIgnoreCase("ff"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\selenium\\geckodriver.exe");
			driver=new FirefoxDriver();
			
		}
		else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver","C:\\selenium\\chromedriver.exe");
			driver=new ChromeDriver();
			
			
		}

	}

	@BeforeMethod//Lunch url and verifying title
	public void lunchUrl() {
		driver.get(url);
		String exp = "twoPLUGS - A plug for your Service and another for your Need";
		String act = driver.getTitle();
		Assert.assertEquals(act, exp);

	}

	@Test(priority=1) // login with username "sun" and password "test2plug"
	public void login() {
		driver.findElement(By.xpath(
				"//body[@class='main-template']/div[@class='wrapper']/header/div[@class='container']/ul[@class='control-bar']/li[1]/a[1]"))
				.click();
		driver.findElement(By.xpath("//input[@name='email']")).clear();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("sun");
		;
		driver.findElement(By.xpath("//input[@id='signInPassword']")).clear();
		driver.findElement(By.xpath("//input[@id='signInPassword']")).sendKeys("test2plug");
		driver.findElement(By.xpath("//ul[@class='line-btn']//button[@class='btn btn-success w-btn-success']")).click();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='name']")));
		String act = driver.findElement(By.xpath("//div[@class='name']")).getText();// Assertion for user "Sun"
		Assert.assertTrue(act.contains("Sun"));

	}

	@Test(priority=2)//Testing whether user can search a service
	public void searchService() throws InterruptedException {
		WebElement se = driver.findElement(By.cssSelector("#exampleInputAmount"));
		se.sendKeys("dog walker");// Search for the service/need dog walker
		se.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		String serv = driver.findElement(By.xpath("//a[contains(text(),'Services')]")).getText();// If text is "Service"
																									// click on service
		if (serv.equalsIgnoreCase("services")) {
			driver.findElement(By.xpath("//a[contains(text(),'Services')]")).click();
			String actual = driver.findElement(By.xpath("//div[@class='title'] /a[1]")).getText();

			System.out.println(actual);
			Assert.assertTrue(actual.contains("DOG"));//Assertion for a service
			System.out.println("user can serch for  service successfully");
		}
			}
	
	@Test(priority=3)//Testing whether user can search for a need
	public void searchNeed() throws InterruptedException
	{
		WebElement se = driver.findElement(By.xpath("//input[@name='q']"));
		se.sendKeys("dog walker");// Search for the need dog walker
		se.sendKeys(Keys.ENTER);
		WebDriverWait wait=new WebDriverWait(driver, 5);//explicit wait added
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Needs')]")));
		//Thread.sleep(5000);
		String need=driver.findElement(By.xpath("//a[contains(text(),'Needs')]")).getText();
		if(need.equalsIgnoreCase("needs"))
		{
			driver.findElement(By.xpath("//a[contains(text(),'Services')]")).click();
			String actual = driver.findElement(By.xpath("//div[@class='title'] /a[1]")).getText();

			System.out.println(actual);
			Assert.assertTrue(actual.contains("WALKER"));//Assertion for a NEED
			System.out.println("user can search for need successfully");
		}
	}
@Test(priority=4)//Testing whether user can search for other user
public void searchUser()
{
	WebElement user=driver.findElement(By.xpath("//input[@name='q']"));
	user.sendKeys("Main");//Searching for the user main
	user.sendKeys(Keys.ENTER);
	WebDriverWait wait=new WebDriverWait(driver,5);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'main triner')]")));
	driver.findElement(By.xpath("//a[contains(text(),'main triner')]")).click();
	String actual=driver.findElement(By.xpath("//a[contains(text(),'Main')]")).getText();
	String  expect="Main";
	Assert.assertEquals(actual, expect);//Assertion for user "Main"
	System.out.println("user can search for other user");
}
		@AfterMethod
		public void home()//Navigate to twoplugs home
		{
			driver.findElement(By.xpath("//img[@src='/newlayout/icons/logo.png']")).click();
			
		}
	@AfterClass
	public void close()//closing browser
	{
		driver.close();
	}
}
