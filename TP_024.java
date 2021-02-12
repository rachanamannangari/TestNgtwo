package twoplug;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TP_024 {
	public WebDriver driver;
	public String url = "https://qatest.twoplugs.com";
	@BeforeClass
	@Parameters("browser")//cross browser testing
public void browserLunch(String browser)
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

@Test(priority=1)//login
public void login() {
	  driver.findElement(By.xpath("//body[@class='main-template']/div[@class='wrapper']/header/div[@class='container']/ul[@class='control-bar']/li[1]/a[1]")).click();
		driver.findElement(By.xpath("//input[@name='email']")).clear();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("sun");//user name sun
		
		driver.findElement(By.xpath("//input[@id='signInPassword']")).clear();
		driver.findElement(By.xpath("//input[@id='signInPassword']")).sendKeys("test2plug");//password test2plug
		driver.findElement(By.xpath("//ul[@class='line-btn']//button[@class='btn btn-success w-btn-success']")).click();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='name']")));
		String act = driver.findElement(By.xpath("//div[@class='name']")).getText();// Assertion for user "Sun"
		Assert.assertTrue(act.contains("Sun"));
}
 @Test(priority=2)//Testing whether a user can refer a friend
 public void referFriend() throws InterruptedException
 {
	 //Thread.sleep(5000);
	 WebElement user=driver.findElement(By.xpath("//span[@class='caret']"));//mouse action->click on hi user"));//mouse action->click on hi user
	 Actions  mouse=new Actions(driver);
	 mouse.moveToElement(user).click().build().perform();
	 Thread.sleep(5000);
	WebElement setting=driver.findElement(By.xpath("//a[@href='/settings/Password']//span[@class='help'][contains(text(),'Settings')]"));
		mouse.moveToElement(setting).click().build().perform();//move to settings
		Thread.sleep(6000);
		driver.findElement(By.xpath("//span[contains(text(),'Refer a Friend')]")).click();// click on refer a friend
		driver.findElement(By.xpath("//*[@name=\"email_input\"]")).sendKeys("rachana.bijoy@gmail.com");// enter email id
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String text = driver.findElement(By.xpath("//p[contains(text(),'The email has already been taken.')]"))
				.getText();// this email id is already taken
		Assert.assertTrue(text.contains("taken"));// Assertion
 }
 @AfterClass
 public void closeBrowser()
 {
 	  driver.close();
 }
}
