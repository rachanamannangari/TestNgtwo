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

public class TP_013 {
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
 @Test(priority=2)//Testing whether he can bid the  the need by clicking on Bid button
 public void biddingNeed() throws InterruptedException
 {
	 WebElement search = driver.findElement(By.xpath("//input[@name='q']"));//click on search
		search.sendKeys("baby sitter");//searching need
		search.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		int rowcount=driver.findElements(By.xpath("/html/body/div[7]/div/div[2]/div/table/tbody/tr")).size();
		System.out.println("count is "+rowcount);
		for(int r=1; r<=rowcount; r++)
		{
			
			String need=driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/div/table/tbody/tr["+r+"]/td[1]/div/div[2]/div[1]/a")).getText();
			if(need.equalsIgnoreCase("baby sitter"))//if need is baby sitter,click on link
			{
			
			driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/div/table/tbody/tr["+r+"]/td[1]/div/div[2]/div[1]/a")).click();
			}
			String check=driver.findElement(By.xpath("//div[contains(text(),'Need Details')]")).getText();
				
			//String text = driver.findElement(By.xpath("//tr/td/a")).getText();
			if (check.contains("Need"))//checking whether it is need
			{
				System.out.println("search successfull"); 
				break;
			}
			else {
				driver.navigate().back();//navigate to previous page
				//continue;
			}
		}
		
		driver.findElement(By.xpath("//a[@class='btn w-btn-success']")).click();
		Thread.sleep(5000);
	driver.findElement(By.xpath("//*[@id='price']")).clear();
	//Thread.sleep(8000);
	driver.findElement(By.xpath("//*[@id='price']")).sendKeys("20");//add bidding amount
	
	Thread.sleep(5000);
	driver.findElement(By.xpath("//div[@id='agreeterm-styler']")).click();//click on check box
	
	driver.findElement(By.xpath("//span[contains(text(),'send')]")).click();//click on send button
	//Thread.sleep(5000);
		String text=driver.findElement(By.xpath("//label[contains(text(),'Bidding a Need')]")).getText();
Assert.assertTrue(text.contains("Bidding a Need"));//Added assertion
 }
 
 @AfterMethod
	public void home() {
		WebDriverWait wait = new WebDriverWait(driver, 30);//click on "Twoplugs"
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/newlayout/icons/logo.png']")));
		
	}
@AfterClass
public void closeBrowser()
{
	  driver.close();
}
}
