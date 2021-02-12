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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TP_016 {
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
 public void addServicematch() throws InterruptedException
 {
	 Thread.sleep(5000);
	 WebElement user=driver.findElement(By.xpath("//span[@class='caret']"));//mouse action->click on hi user"));//mouse action->click on hi user
	 Actions  mouse=new Actions(driver);
	 mouse.moveToElement(user).click().build().perform();
	WebElement setting=driver.findElement(By.xpath("//a[@href='/settings/Password']//span[@class='help'][contains(text(),'Settings')]"));
		mouse.moveToElement(setting).click().build().perform();//move to settings
		Thread.sleep(6000);
		driver.findElement(By.xpath("//span[contains(text(),'Service Match')]")).click();//click on service match
		driver.findElement(By.xpath("//div[contains(text(),'Service')]")).click();//select service
		driver.findElement(By.xpath("//div[@class='jq-selectbox__select-text placeholder']")).click();//select category
		Thread.sleep(5000);
		driver.findElement(By.xpath("//li[contains(text(),'Animals & Agriculture')]")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//*[@id=\"subcategory_id-styler\"]/div[1]/div[1]")).click();//click on sub category
		
		driver.findElement(By.xpath("//li[contains(text(),'Pet')]")).click();
		
		driver.findElement(By.xpath("//div[4]//button[1]")).click();//click on add
	 String text1=	driver.findElement(By.xpath("//div[contains(text(),'this category has already exist in list')]")).getAttribute("innerText");
 System.out.println("text is" +text1);
	Assert.assertTrue(text1.contains("EXIST"));//Assertion added
			
	
 }
 @AfterMethod
	public void home() {
		WebDriverWait wait = new WebDriverWait(driver, 30);//click on "Twoplugs"
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/newlayout/icons/logo.png']")));
		
	}
@AfterClass
public void closeBrowser()//close browser
{
	  driver.close();
}
 
 
 
}
