package twoplug;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//TP-002-->Login Page > Valid Email/user name & password and TP-003-->Login Page > invalid Email/user name & password
public class TP_002TP_003 {

	public WebDriver driver;
	public String url = "https://qatest.twoplugs.com";

	@BeforeClass
	@Parameters("browser")//cross browser testing
	public void browserlunch( String browser)
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
	public void checkTitle() {
		driver.get(url);
		String act = driver.getTitle();
		Assert.assertTrue(act.contains("twoPLUGS"));//checking the title of the page
	}

	@Test(dataProvider = "credential")//passing credentials using DataProvider annotation
	public void login(String username, String password) {

		driver.findElement(By.xpath("//body[@class='main-template']/div[@class='wrapper']/header/div[@class='container']/ul[@class='control-bar']/li[1]/a[1]"))
				.click();//Click on login
		driver.findElement(By.xpath("//input[@name='email']")).clear();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);//enter username
		
		driver.findElement(By.xpath("//input[@id='signInPassword']")).clear();
		driver.findElement(By.xpath("//input[@id='signInPassword']")).sendKeys(password);//enter password
		driver.findElement(By.xpath("//ul[@class='line-btn']//button[@class='btn btn-success w-btn-success']")).click();//click on login
		if (username.equalsIgnoreCase("rachana")) {
			String act = driver.findElement(By.xpath("//div[@class='alert alert-danger text-center']")).getText();////Testing whether username is rachana

			String exp = "INVALID EMAIL/PASSWORD.";
			Assert.assertEquals(act, exp);//Assertion for invalid username and password
		} else {
			WebDriverWait wait=new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='name']")));//Testing whether username is sun
			String act = driver.findElement(By.xpath("//div[@class='name']")).getText();
			Assert.assertTrue(act.contains("Sun"));//Assertion for valid username and password
		}

	}

	@DataProvider(name = "credential")//using dataprovider annotation
	public String[][] credentials() {
		String data[][] = { { "rachana", "test2plug" }, { "sun", "test2plug" } };//passing invalid username  and password ,valid username and password
		return data;

	}

	@AfterClass
	public void close() {
		driver.close();//close browser
	}
}
