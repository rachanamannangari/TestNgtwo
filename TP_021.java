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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TP_021 {
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
		 
@Test(priority=2)//Testing whether the footer links are taking to the appropriate page
public void footerLinks() throws InterruptedException
{
	
driver.findElement(By.xpath("/html/body/footer/div/ul/li[1]/a")).click();//click on Feedback
String text=driver.findElement(By.xpath("/html/body/div[7]/div/div/form/div[1]/h4")).getText();
System.out.println("text is "+text);
Assert.assertTrue(text.equalsIgnoreCase("We want to hear from you"));//Assertion

driver.findElement(By.xpath("/html/body/footer/div/ul/li[2]/a")).click();//click on support
String text1=driver.findElement(By.xpath("//div[contains(text(),'Support Center Topics')]")).getText();
System.out.println("text is "+text1);
Assert.assertTrue(text1.contains("Support Center"));//Assertion

driver.findElement(By.xpath("/html/body/footer/div/ul/li[3]/a")).click();//click on "privacy policy"
String text3=driver.findElement(By.xpath("//h2[contains(text(),'Privacy Policy')]")).getText();
System.out.println("text is "+text3);
Assert.assertTrue(text3.contains("Privacy"));//Assertion

driver.findElement(By.xpath("/html/body/footer/div/ul/li[4]/a")).click();//click on "Terms of use"
String text4=driver.findElement(By.xpath("//h2[contains(text(),'Terms of Use')]")).getText();
System.out.println("text is "+text4);
Assert.assertTrue(text4.contains("Terms of Use"));//Assertion

	
}

@AfterClass
 public void close()//close browser
 {
	  driver.close();
 } 
	
}


