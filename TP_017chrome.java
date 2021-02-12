package twoplug;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class TP_017chrome {
	public WebDriver driver;
	public String url = "https://qatest.twoplugs.com";

	@BeforeClass
	 @Parameters("browser")//cross browser testing
	public void browserLunch(String browser) {
		 if(browser.equalsIgnoreCase("ff"))
		 {
		System.setProperty("webdriver.gecko.driver", "C:\\selenium\\geckodriver.exe");
		driver = new FirefoxDriver();

		 }
else if(browser.equalsIgnoreCase("chrome")){
		 System.setProperty("webdriver.chrome.driver","C:\\selenium\\chromedriver.exe");
		 driver=new ChromeDriver();

		 }

	}

	@BeforeMethod // Lunch url and verifying title
	public void lunchUrl() {
		driver.get(url);
		String exp = "twoPLUGS - A plug for your Service and another for your Need";
		String act = driver.getTitle();
		Assert.assertEquals(act, exp);

	}

	@Test(priority = 1) // login
	public void login() {
		driver.findElement(By.xpath(
				"//body[@class='main-template']/div[@class='wrapper']/header/div[@class='container']/ul[@class='control-bar']/li[1]/a[1]"))
				.click();
		driver.findElement(By.xpath("//input[@name='email']")).clear();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("sun");// username sun

		driver.findElement(By.xpath("//input[@id='signInPassword']")).clear();
		driver.findElement(By.xpath("//input[@id='signInPassword']")).sendKeys("test2plug");// password test2plug
		driver.findElement(By.xpath("//ul[@class='line-btn']//button[@class='btn btn-success w-btn-success']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='name']")));
		String act = driver.findElement(By.xpath("//div[@class='name']")).getText();// Assertion for user "Sun"
		Assert.assertTrue(act.contains("Sun"));
	}

	@Test(priority = 2)//'Testing whether he can file a complaint after the transaction
	public void fileComplaint() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[7]/nav[1]/div/div[2]/ul/li[2]/a/span[2]")).click();
		Thread.sleep(5000);
		int count = driver.findElements(By.xpath("/html/body/div[7]/div[1]/table[2]/tbody/tr")).size();//number of pages
		System.out.println("count is" + count);
		int page = driver.findElements(By.xpath("/html/body/div[7]/nav[2]/ul/li")).size();//number of raws
		String service="";
		int r;
		System.out.println("Page------------>"+page);
		for (int p = 1; p <= page; p++) {
			System.out.println("Outer for loop counter--->"+p);
			count = driver.findElements(By.xpath("/html/body/div[7]/div[1]/table[2]/tbody/tr")).size();
			System.out.println("count------------>"+count);
			for ( r = 1; r <= count; r++)
				
			{
				System.out.println("Inner for loop counter--->"+r);
				System.out.println("------1");
				//WebElement element=driver.findElement(By.xpath("/html/body/div[7]/div[1]/table[2]/tbody/tr["+r+"]/td[1]/div/div"));
				//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				//Thread.sleep(5000);
			 service = driver.findElement(By.xpath("/html/body/div[7]/div[1]/table[2]/tbody/tr["+r+"]/td[1]/div/div")).getText();//get text of the element
			
				if (service.equalsIgnoreCase("dog walker")) {
					System.out.println("------2");
					//Thread.sleep(5000);
					
				WebElement complaint=	driver.findElement(By.xpath("/html/body/div[7]/div[1]/table[2]/tbody/tr["+r+"]/td[7]/a[2]/i"));
					//driver.findElement(By.xpath("/html/body/div[7]/div[1]/table[2]/tbody/tr["+r+"]/td[7]/a[2]/i")).click();//-->working in firefox
			//	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", complaint);
				Actions mouse=new Actions(driver);
					//Thread.sleep(5000);
					mouse.moveToElement(complaint).click(complaint).build().perform();//move to complaint icon and click on icon
					
			
					///html/body/div[7]/div[1]/table[2]/tbody/tr[1]/td[7]/a[2]/i
					System.out.println("------3");
					break;
				}
				
				else {
					System.out.println("------4");
					continue;
				}
			}
			System.out.println("------5");
			if (service.equalsIgnoreCase("dog walker")) {
				System.out.println("------2");
				
				//driver.findElement(By.xpath("/html/body/div[7]/div[1]/table[2]/tbody/tr["+r+"]/td[7]/a[2]/i")).click();
				System.out.println("------3");
				break;
			}
			
			driver.findElement(By.xpath("/html/body/div[7]/nav[2]/ul/li[" + p + "]")).click();//click on next page
		//	/html/body/div[7]/nav[2]/ul/li[2]/a
			System.out.println("------6");
		}
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"reportSubject\"]")).sendKeys("service is not good");//open complaint box and enter subject
		driver.findElement(By.xpath("//*[@id=\"compliantform\"]/div[4]/div/textarea"))
				.sendKeys("I dont want to continue this service");//enter content

		driver.findElement(By.xpath("//*[@id=\"compliantform\"]/div[5]/ul/li[2]/button")).click();//submit
		
		Thread.sleep(5000);
		
		String text = driver
				.findElement(By.xpath("//div[contains(text(),'Your complaint has been submitted. You will be con')]"))
				.getAttribute("innerText");
		Assert.assertTrue(text.contains("Your complaint has been submitted."));//assertion

	}

	
	  @AfterClass public void browserClose(){
	 
	  driver.close(); 
	  }
	 

}
