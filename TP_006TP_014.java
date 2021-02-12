package twoplug;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TP_006TP_014 {
	public WebDriver driver;
	public String url = "https://qatest.twoplugs.com/";

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

	@Test(priority = 1)
// login with user name "sun" and password "test2plug"
	public void login() throws InterruptedException {
		driver.get(url);
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"//body[@class='main-template']/div[@class='wrapper']/header/div[@class='container']/ul[@class='control-bar']/li[1]/a[1]"))
				.click();
		driver.findElement(By.xpath("//input[@name='email']")).clear();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("sun");

		driver.findElement(By.xpath("//input[@id='signInPassword']")).clear();
		driver.findElement(By.xpath("//input[@id='signInPassword']")).sendKeys("test2plug");
		WebDriverWait wait=new WebDriverWait(driver,30);
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='line-btn']//button[@class='btn btn-success w-btn-success']")));
		driver.findElement(By.xpath("//ul[@class='line-btn']//button[@class='btn btn-success w-btn-success']")).click();
		//Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='name']")));
		String act = driver.findElement(By.xpath("//div[@class='name']")).getText();// Assertion for user "Sun"
		Assert.assertTrue(act.contains("Sun"));

	}

	@Test(priority = 3, enabled = false)
	public void searchUser() throws InterruptedException {

		WebElement search = driver.findElement(By.xpath("//input[@name='q']"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(search));
		search.sendKeys("jack");// Searching for the user jack
		search.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		WebElement user = driver.findElement(By.xpath("//table[@class='result-table'] /tbody"));// Assign all
																								// search
		int count = user.findElements(By.tagName("tr")).size();
		// in to
		// List
		// int count = user.size();// total number of search result

		System.out.println(" Search result is " + count);
		for (int i = 0; i <= count; i++) {
			Thread.sleep(5000);
			// driver.navigate().refresh();

			user.findElements(By.tagName("tr")).get(i).findElement(By.xpath("//a[@class='img']")).click(); // result
			String text = driver.findElement(By.xpath("//tr/td/a")).getText();// navigate to

			if (text.equalsIgnoreCase("jack"))// Verifying whether user name is jack
			{
				Assert.assertTrue(text.equalsIgnoreCase("jack"));// Add assertion if its true exit the loop
				break;
				// driver.findElement(By.xpath("//tr/td/a")).click();//Click on jack

				// driver.findElement(By.xpath("//span[@class='w-icons-profileCtrl1']")).click();
			} else {
				driver.navigate().back();

			}

		}

	}

	@Test(priority = 2, dependsOnMethods = { "login" })//Testing whether  whether user can follow other user
	public void followUser() throws InterruptedException {
		WebElement search = driver.findElement(By.xpath("//input[@name='q']"));
		search.sendKeys("jack");// Searching for the user jack
		search.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		WebElement user = driver.findElement(By.xpath("//table[@class='result-table'] /tbody"));
		user.findElements(By.tagName("tr")).get(0).findElement(By.xpath("//a[@class='img']")).click();//click on the first result in the search result
		driver.findElement(By.xpath("//tr/td/a")).click();//click on other user name
		driver.findElement(By.xpath("//span[@class='w-icons-profileCtrl1']")).click();//click on follow link
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@id='profile_btn_follow'] /span")).click();
		
	String actual = driver.findElement(By.xpath("//div[@id='followModalmessage'] /div")).getAttribute("innerText");// getting the text from the element
		System.out.println("message is" + actual);
		
		Assert.assertTrue(actual.contains("now following"));//Added assertion

	}

	@Test(priority = 4)//testing whether user can transfer the eeds to other user
	public void sendDeeds() throws InterruptedException {

		WebElement search = driver.findElement(By.xpath("//input[@name='q']"));
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.elementToBeClickable(search));
		Thread.sleep(5000);
		search.sendKeys("jack");// Searching for the user jack
		search.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		WebElement user = driver.findElement(By.xpath("//table[@class='result-table'] /tbody"));
		int count = user.findElements(By.tagName("tr")).size();//taking count of search result
		System.out.println("count is" + count);

		user.findElements(By.tagName("tr")).get(0).findElement(By.xpath("//a[@class='img']")).click();//click on first result
		// String text = driver.findElement(By.xpath("//tr/td/a")).getText();
		driver.findElement(By.xpath("//tr/td/a")).click();//click on other user name
		driver.findElement(By.xpath("//li[@title='Send eeds'] /a/span")).click();//click on send eeds tab
		driver.findElement(By.xpath("//input[@name='amount']")).sendKeys("10");//enter amount
		driver.findElement(By.xpath("//a[@id='transfer_id'] /span")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@id='btn_transfer'] /span")).click();//click on transfer
		Thread.sleep(5000);
		String text = driver.findElement(By.xpath("//div[contains(text(),'Credit Transfer was successful')]"))//Retrieving innertext of the element
				.getAttribute("innerText");
		System.out.println("transfer" + text);
		Assert.assertTrue(text.contains("Transfer was successful"));//Added assertion
	}

	@Test(priority = 5)//Testing whether the user can send message to other user/admin
	public void sendMessage() throws InterruptedException {
		WebElement search = driver.findElement(By.xpath("//input[@name='q']"));
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.elementToBeClickable(search));
		Thread.sleep(5000);
		search.sendKeys("jack");// Searching for the user jack
		search.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		WebElement user = driver.findElement(By.xpath("//table[@class='result-table'] /tbody"));
		int count = user.findElements(By.tagName("tr")).size();
		System.out.println("count is" + count);

		user.findElements(By.tagName("tr")).get(0).findElement(By.xpath("//a[@class='img']")).click();
		// String text = driver.findElement(By.xpath("//tr/td/a")).getText();
		driver.findElement(By.xpath("//tr/td/a")).click();
		driver.findElement(By.xpath("//a[@id='messageforperosnal'] /span")).click();//click on message tab
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='messagetitle']")).sendKeys("testing");//enter title
		driver.findElement(By.xpath("//textarea[@id='messagecontent']")).sendKeys("Hi");//enter content
		driver.findElement(By.xpath("//button[@id='message_send']")).click();//click on send
		Thread.sleep(5000);
		String text = driver.findElement(By.xpath("//div[contains(text(),'Message sent successfully')]"))
				.getAttribute("innerText");
		System.out.println("message " + text);
		Assert.assertTrue(text.contains("sent successfully"));//Added assertion

	}
	@Test(priority=6)//Testing whether 'report user' is working or not
	public void reportUser() throws InterruptedException
	{
		WebElement search = driver.findElement(By.xpath("//input[@name='q']"));
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.elementToBeClickable(search));
		Thread.sleep(5000);
		search.sendKeys("jack");// Searching for the user jack
		search.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		WebElement user = driver.findElement(By.xpath("//table[@class='result-table'] /tbody"));
		int count = user.findElements(By.tagName("tr")).size();
		System.out.println("count is" + count);

		user.findElements(By.tagName("tr")).get(0).findElement(By.xpath("//a[@class='img']")).click();
		// String text = driver.findElement(By.xpath("//tr/td/a")).getText();
		driver.findElement(By.xpath("//tr/td/a")).click();
		Thread.sleep(5000);
		//driver.findElement(By.xpath("//a[@href='/complaintUser/Jack'] /span")).click();
		///html/body/div[7]/section/div[1]/div/div[2]/div[2]/ul/li[4]/a/span
		driver.findElement(By.xpath("//span[@class='w-icons-profileCtrl4']")).click();
		
		driver.findElement(By.xpath("//input[@id='reportSubject']")).sendKeys("testing service");
		driver.findElement(By.xpath("//div[@class='form-controls'] /textarea")).sendKeys("service is poor");
		driver.findElement(By.xpath("//button[@type='submit'] /span")).click();
		Thread.sleep(5000);
	String text=driver.findElement(By.cssSelector("body:nth-child(2) div.modal.fade.dismiss-modal:nth-child(7) div.modal-dialog > div.alert.alert-default.text-center")).getAttribute("innerText");
	System.out.println("report user" +text);
	Assert.assertTrue(text.contains("submitted."));//Added assertion
	}

	@AfterMethod
	public void home() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/newlayout/icons/logo.png']")));
	}

	@AfterClass
	public void browserClose() {
		driver.close();
	}

}
