package twoplug;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TP_007 {
	
	public WebDriver driver;
	@BeforeClass
	@Parameters("browser")//cross browser testing
	public void browserlunch(String browser)
	{
		if(browser.equalsIgnoreCase("ff"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\selenium\\geckodriver.exe");
			driver=new FirefoxDriver();
			driver.get("https://qatest.twoplugs.com");
			
		}
		else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver","C:\\selenium\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.get("https://qatest.twoplugs.com");
			
			
		}

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
  @Test(priority=2,enabled=true)//creating new service
  public void createService() throws InterruptedException
  {
	 // WebDriverWait wait=new WebDriverWait(driver, 30);
//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='w-icons-create']")));
	  driver.findElement(By.xpath("//div[@class='create'] /span")).click();//click on "+" icon
Actions mouse=new Actions(driver);
WebElement service= driver.findElement(By.xpath("//a[@href='/addservices/1']"));
mouse.moveToElement(service).click().build().perform();//click on service link
Thread.sleep(5000);
driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Testing");//enter service details
driver.findElement(By.xpath("//textarea[@id='description']")).sendKeys("creating new service");
driver.findElement(By.xpath("//div[@class='jq-selectbox__select-text placeholder']")).click();
//WebElement drop=driver.findElement(By.xpath("//div[@class='jq-selectbox__select-text placeholder']"));
//Select se=new Select(drop);
//se.selectByIndex(1);
WebElement category=driver.findElement(By.xpath("//li[contains(text(),'Animals & Agriculture')]"));//select category
mouse.moveToElement(category).click().build().perform();
//Select se1=new Select(driver.findElement(By.xpath(" //div[contains(text(),'All')]")));

//se1.selectByIndex(2);

//driver.findElement(By.xpath("//div[contains(text(),'All')]")).click();
Thread.sleep(5000);
driver.findElement(By.xpath("//div[@class='jq-selectbox jqselect']//div[@class='jq-selectbox__trigger-arrow']")).click();
//Thread.sleep(5000);
//JavascriptExecutor js = (JavascriptExecutor) driver;
//js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//js.executeScript("arguments[0].scrollIntoView();", Element);
WebElement sub=driver.findElement(By.xpath("//li[contains(text(),'Pet')]"));//select sub category
//js.executeScript("arguments[0].scrollIntoView();", sub);
Thread.sleep(5000);
mouse.moveToElement(sub).click().build().perform();//mouse action
driver.findElement(By.xpath("//input[@name='price']")).sendKeys("100");
//driver.findElement(By.xpath("//div[@id='slider-range-max3']//span[@class='ui-slider-handle ui-state-default ui-corner-all']")).click();
Thread.sleep(5000);
//WebElement slider=driver.findElement(By.xpath("/html[1]/body[1]/div[7]/div[1]/form[1]/div[3]/div[2]/div[1]/div[1]/span[1]"));
//mouse.dragAndDropBy(slider, 0, 10).build().perform();
//Thread.sleep(5000);

//WebDriverWait wait=new WebDriverWait(driver, 30);
//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='ui-slider-handle ui-state-default ui-corner-all'] /span[contains(text(),'0')]")));
//WebElement source=driver.findElement(By.xpath("//span[@class='ui-slider-handle ui-state-default ui-corner-all'] /span[contains(text(),'0')]"));
//WebElement source=driver.findElement(By.cssSelector("#amount3"));
//WebElement slid=driver.findElement(By.cssSelector("#slider-range-max3"));
//Point point =slid.getLocation();
//int x=point.getX();
//int y=point.getY();
//System.out.println("X is " +x +"y is " +y);
//WebElement source=driver.findElement(By.xpath("/html[1]/body[1]/div[7]/div[1]/form[1]/div[3]/div[2]/div[1]/div[1]/span[1]"));

//mouse.dragAndDropBy(slider,0,0).build().perform();
//WebElement target=driver.findElement(By.xpath("/html[1]/body[1]/div[7]/div[1]/form[1]/div[3]/div[2]/div[1]/div[1]/span[1]/span[1]"));
//mouse.dragAndDrop(slider,target).click().build().perform();
//mouse.clickAndHold(slider).moveToElement(target).release().build().perform();
//int width=slid.getSize().getWidth();
//System.out.println("wodth is" +width);
//mouse.moveToElement(target, .1, 0).
//Thread.sleep(6000);
//mouse.moveToElement(slid, width/100,0).click().build().perform();
//WebElement ele=driver.findElement(By.xpath("//div[@id='slider-range-max3']//span[@class='ui-slider-handle ui-state-default ui-corner-all']"));
//Actions act=new Actions(driver);
//act.moveToElement(ele).dragAndDropBy(ele,100,0).build().perform();

//driver.findElement(By.xpath(.//*[normalize-space(text()) and normalize-space(.)='Refund %'])[1]/following::span[1]")).click();
driver.findElement(By.xpath("//input[@name='refund_valid']")).sendKeys("0");
WebElement img=driver.findElement(By.xpath("//input[@name='pic1']"));
//img.sendKeys("C:\\Users\\BIJOY\\Desktop\\DSC_0740.jpeg");
driver.findElement(By.xpath("//div[@title='When active, users can buy this service']")).click();
driver.findElement(By.xpath("//span[contains(text(),'create')]")).click();//click on create service
String creat=driver.findElement(By.xpath("//div[contains(text(),'Service has been added')]")).getAttribute("innerText");
Assert.assertTrue(creat.contains("added"));//assertion added
  }
  @Test(priority=3)//editing service
  public void updateService() throws InterruptedException
  {
	  Thread.sleep(5000);
			WebElement se = driver.findElement(By.cssSelector("#exampleInputAmount"));
			se.sendKeys("Testing");// Search for the service Testing
			se.sendKeys(Keys.ENTER);
			Thread.sleep(5000);
			//WebDriverWait wait=new WebDriverWait(driver, 30);
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/service/15067'][contains(text(),'Testing')]")));
			driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/div/table/tbody/tr[1]/td[1]/div/div[2]/div[1]/a")).click();		
			//driver.findElement(By.xpath("//a[@href='/service/15067'][contains(text(),'Testing')]")).click();
			//driver.findElement(By.xpath("//a[@href='/service/15144'][contains(text(),'testing')]")).click();
			driver.findElement(By.xpath("//a[@class='pull-right edit-link']")).click();
			driver.findElement(By.xpath("//input[@name='price']")).clear();
			driver.findElement(By.xpath("//input[@name='price']")).sendKeys("200");//editing price field
			Thread.sleep(3000);
			driver.findElement(By.xpath("//span[contains(text(),'Save')]")).click();	
		String update=	driver.findElement(By.xpath("//html[1]/body[1]/div[7]/div[1]/div[1]")).getAttribute("innerText");
		
		Assert.assertTrue(update.contains("updated"));//added assertion
			
			
  }
  @Test(priority=4)
  public void deleteService() throws InterruptedException //deleting service
  {
	 // driver.findElement(By.xpath("//span[contains(text(),'Hi,Sun')]")).click();
	  Thread.sleep(5000);
	  WebElement user=driver.findElement(By.xpath("//span[@class='caret']"));//mouse action->click on hi user
	 Actions  mouse=new Actions(driver);
	 mouse.moveToElement(user).click().build().perform();
		WebElement profile=driver.findElement(By.xpath("//span[contains(text(),'Profile')]"));
		mouse.moveToElement(profile).click().build().perform();//move to profile
		Thread.sleep(5000);
		driver.findElement(By.xpath("//tr[1]//td[4]//ul[1]//li[2]//a[1]//span[1]")).click();//click on icon
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[contains(text(),'I want to delete')]")).click();
		Thread.sleep(5000);
	String delete=	driver.findElement(By.xpath("//div[contains(text(),'Service has been deleted')]")).getAttribute("innerText");
  Assert.assertTrue(delete.contains("deleted"));
  
  
  }
  
  
  @AfterMethod
	public void home() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/newlayout/icons/logo.png']")));
		
	}
  @AfterClass
  public void close()
  {
	  driver.close();
  }
  
}
