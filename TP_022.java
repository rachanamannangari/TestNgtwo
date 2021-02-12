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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TP_022 {
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
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("sun");//username sun
		
		driver.findElement(By.xpath("//input[@id='signInPassword']")).clear();
		driver.findElement(By.xpath("//input[@id='signInPassword']")).sendKeys("test2plug");//password test2plug
		driver.findElement(By.xpath("//ul[@class='line-btn']//button[@class='btn btn-success w-btn-success']")).click();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='name']")));
		String act = driver.findElement(By.xpath("//div[@class='name']")).getText();// Assertion for user "Sun"
		Assert.assertTrue(act.contains("Sun"));
  }
 @Test(priority=2)//Testing whether user can  add service match to his settings
 public void changeEmail() throws InterruptedException
 {
	 Thread.sleep(5000);
	 WebElement user=driver.findElement(By.xpath("//span[@class='caret']"));//mouse action->click on hi user"));//mouse action->click on hi user
	 Actions  mouse=new Actions(driver);
	 mouse.moveToElement(user).click().build().perform();
	 Thread.sleep(5000);
	WebElement setting=driver.findElement(By.xpath("//a[@href='/settings/Password']//span[@class='help'][contains(text(),'Settings')]"));
		mouse.moveToElement(setting).click().build().perform();//move to settings
		Thread.sleep(6000);
		driver.findElement(By.xpath("/html/body/div[7]/div[1]/form/div[1]/nav/ul/li[6]/a")).click();
		driver.findElement(By.xpath("//*[@name=\"new_email\"]")).sendKeys("sun1@gmail.com");
		driver.findElement(By.xpath("//*[@name=\"confirm_email\"]")).sendKeys("sun1@gmail.com");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@name=\"new_email_password\"]")).sendKeys("test2plug");
		driver.findElement(By.xpath("/html/body/div[7]/div/form/div[2]/ul/li[2]/button/span")).click();
		String text=driver.findElement(By.xpath("//div[contains(text(),'Your email has been updated')]")).getAttribute("innerText");
		Assert.assertTrue(text.contains("updated"));
		
		
}
}
