package twoplug;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TP_001 {
	public WebDriver driver;
	public  String url="https://qatest.twoplugs.com";
	@BeforeTest
	@Parameters("browser")
	public void browserlunch(String browser)//cross browser testing
	{
		if(browser.equalsIgnoreCase("ff"))//Firefox
		{
			System.setProperty("webdriver.gecko.driver","C:\\selenium\\geckodriver.exe");
			driver=new FirefoxDriver();
			
		}
		else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver","C:\\selenium\\chromedriver.exe");//chrome
			driver=new ChromeDriver();
			
			
		}
	}
	@BeforeMethod
	public void home()
	{
		driver.get(url);//get url
		String exp="twoPLUGS - A plug for your Service and another for your Need";
		String act=driver.getTitle();//Testing the title of the page
		Assert.assertEquals(act, exp);
		
		
		
	}
		@Test(priority=1)//sign up for invalid user
		  public void userName (){
			//driver.get(url);
			
		driver.findElement(By.xpath("//body[@class='main-template']/div[@class='wrapper']/header/div[@class='container']/ul[@class='control-bar']/li/a[@class='btn border']/span[1] ")).click();//click on login
		  driver.findElement(By.xpath("//span[contains(text(),'SIGN UP')]")).click();//click on sign up
		  driver.findElement(By.xpath("//input[@name='username']")).sendKeys("@123");//enter invalid  username
		  driver.findElement(By.xpath("//input[@id='signUpEmail']")).sendKeys("bhadra@gmail.com");//enter invalid email
		  driver.findElement(By.xpath("//input[@id='signUpPassword']")).sendKeys("test2plug");//enter password
		  
		  
		  driver.findElement(By.xpath("//button[@type='submit']")).click();//click on submit
	String exp=	driver.findElement(By.xpath("//div[@class='form-controls'] /p[1]")).getText();
		assertTrue(exp.contains("cannot contain special characters"), "inavalid username");//Assertion
		}
		
		@Test(priority = 0)
		public void userName1()
		{
			//driver.findElement(By.xpath("//body[@class='main-template']/div[@class='wrapper']/header/div[@class='container']/ul[@class='control-bar']/li/a[@class='btn border']/span[1] ")).click();
			driver.findElement(By.xpath("//a[@href='/login'] /span[1]")).click();//click on login
			driver.findElement(By.xpath("//span[contains(text(),'SIGN UP')]")).click();//click on sign up
			  driver.findElement(By.xpath("//input[@name='username']")).sendKeys("bhadra");//Enter invalid user name
			  driver.findElement(By.xpath("//input[@id='signUpEmail']")).sendKeys("bhadra@gmail.com");
			  driver.findElement(By.xpath("//input[@id='signUpPassword']")).sendKeys("test2plug");//Enter password
			  
			  
			  driver.findElement(By.xpath("//button[@type='submit']")).click();
		String exp=	driver.findElement(By.xpath("//div[@class='form-controls'] /p[1]")).getText();
		System.out.println(exp);
			assertTrue(exp.contains("username has already been taken"), "inavalid username");//Assertion
		}
		@AfterTest
		public void browserClose()
		{
			driver.close();//closing browser
		}
		  
}
