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


//Testing the functionality creating /updating/deleting a need
public class TP_008 {
  
	  public WebDriver driver;
		public String url = "https://qatest.twoplugs.com";
		@BeforeClass
		@Parameters("browser")//cross browser testing
		public void browserlunch(String browser )
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
		  @Test(priority=2,enabled=true)//creating new need
		  public void createNeed() throws InterruptedException
		  {
			 
			  driver.findElement(By.xpath("//div[@class='create'] /span")).click();//click on "+" icon
		Actions mouse=new Actions(driver);
		WebElement need= driver.findElement(By.xpath("/html/body/div[7]/nav/div/form/div[2]/ul/li[2]/a"));
		mouse.moveToElement(need).click().build().perform();//click on service link
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Testing");//enter service details
		driver.findElement(By.xpath("//textarea[@id='description']")).sendKeys("creating new need");
		driver.findElement(By.xpath("//div[@class='jq-selectbox__select-text placeholder']")).click();
		WebElement category=driver.findElement(By.xpath("//li[contains(text(),'Animals & Agriculture')]"));//select category
		mouse.moveToElement(category).click().build().perform();
				Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='jq-selectbox jqselect']//div[@class='jq-selectbox__trigger-arrow']")).click();
		WebElement sub=driver.findElement(By.xpath("//li[contains(text(),'Pet')]"));//select sub category
		
		Thread.sleep(5000);
		mouse.moveToElement(sub).click().build().perform();//mouse action
		driver.findElement(By.xpath("//input[@name='price']")).sendKeys("100");
		
		Thread.sleep(5000);
				
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='jq-checkbox checked']")).isSelected();
		driver.findElement(By.xpath("//span[contains(text(),'create')]")).click();//click on create service
		String creat=driver.findElement(By.xpath("//div[contains(text(),'Need has been added')]")).getAttribute("innerText");
		Assert.assertTrue(creat.contains("added"));//assertion added
		  }
		  
		@Test(priority=3)//delete need
		public void updateNeed() throws InterruptedException
		{
			Thread.sleep(5000);
			 WebElement user=driver.findElement(By.xpath("//span[@class='caret']"));//mouse action->click on hi user"));//mouse action->click on hi user
			 Actions  mouse=new Actions(driver);
			 mouse.moveToElement(user).click().build().perform();
			WebElement profile=driver.findElement(By.xpath("//span[contains(text(),'Profile')]"));
				mouse.moveToElement(profile).click().build().perform();//move to profile
				Thread.sleep(6000);
	driver.findElement(By.xpath("/html/body/div[7]/div[4]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td[2]/ul/li[1]/a")).click();//click on edit icon
	driver.findElement(By.xpath("//input[@name='price']")).clear();
	driver.findElement(By.xpath("//input[@name='price']")).sendKeys("200");//editing price field
	Thread.sleep(3000);	
	driver.findElement(By.xpath("//span[contains(text(),'Save')]")).click();	
	String update=	driver.findElement(By.xpath("//html[1]/body[1]/div[7]/div[1]/div[1]")).getAttribute("innerText");
	
	Assert.assertTrue(update.contains("updated"));//added assertion
		}
		
		@Test(priority=4)//delete need
		public void deletNeed() throws InterruptedException
		{
			Thread.sleep(5000);
			 WebElement user=driver.findElement(By.xpath("//span[@class='caret']"));//mouse action->click on hi user"));//mouse action->click on hi user
			 Actions  mouse=new Actions(driver);
			 mouse.moveToElement(user).click().build().perform();//mouse action
			WebElement profile=driver.findElement(By.xpath("//span[contains(text(),'Profile')]"));
				mouse.moveToElement(profile).click().build().perform();//move to profile
				Thread.sleep(5000);
				driver.findElement(By.xpath("/html/body/div[7]/div[4]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td[2]/ul/li[2]")).click();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//span[contains(text(),'I want to delete')]")).click();
				Thread.sleep(5000);
			String delete=	driver.findElement(By.xpath("//div[contains(text(),'Need has been deleted')]")).getAttribute("innerText");
		  Assert.assertTrue(delete.contains("deleted"));//assertion added
		
		}
		
		
		 @AfterMethod
			public void home() {
				WebDriverWait wait = new WebDriverWait(driver, 30);//click on "Twoplugs"
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/newlayout/icons/logo.png']")));
				
			}
		  @AfterClass
		  public void close()
		  {
			  driver.close();
		  }
  }

