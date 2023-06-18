package gl_Account_Classes;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/*GLC_1.1_TC_1--> verify class ID field as empty.*/
public class GLC_1_1_TC_1 {
	private WebDriver driver;
	private String URL="http://anuttara.in/business/index.php?application=GL";
	private String ExpErrMSG= "The account class ID cannot be empty.";
	
	@BeforeTest
	private void open() {
		ChromeOptions ch= new ChromeOptions();
		ch.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\Abhishek\\OneDrive\\Desktop\\JAVA_JARS\\driver\\chromedriver.exe");
		driver= new ChromeDriver(ch);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test
	public void Login() throws InterruptedException {
		driver.get(URL);
		Thread.sleep(2000);
		//username field
		driver.findElement(By.xpath("//input[@name='user_name_entry_field']")).clear();
		driver.findElement(By.xpath("//input[@name='user_name_entry_field']")).sendKeys("abhishek");
		Thread.sleep(1000);
		//password field
		driver.findElement(By.xpath("//input[@name='password']")).clear();
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("abhishek");
		Thread.sleep(1000);
		//Login button
		driver.findElement(By.xpath("//input[@name='SubmitUser']")).click();
		Thread.sleep(2000);
		try {
		Alert al= driver.switchTo().alert();
		System.out.println("Popup:"+ al.getText());
		al.accept();
		}
		catch(NoAlertPresentException ex){
			System.out.println(ex);
		}
	}
	@Test (dependsOnMethods = {"Login"})
	private void TC_1() throws InterruptedException {
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@class='tabs']/a[7]")).click();
		Thread.sleep(2000);
		//GL Account Classes
		driver.findElement(By.xpath("//div[@id='_page_body']/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/a[3]")).click();
		//click on add new btn
		driver.findElement(By.xpath("//button[@value='Add new']")).click();
		Thread.sleep(1500);
		//get Actual error message
		String ActErrMsg= driver.findElement(By.xpath("//div[@id='msgbox']/div[1]")).getText();
		
		try {
			assertEquals(ActErrMsg, ExpErrMSG);
			System.out.println("Actual Error="+ActErrMsg);
			System.out.println("Expected Error="+ExpErrMSG);
			System.out.println("GLC_1_1_TC_1 =Passed");
			
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	@AfterTest
	private void close() {
		driver.quit();
	}

}
