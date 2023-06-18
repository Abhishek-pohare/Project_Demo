package gl_Account_Classes;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/*GLC_1.2_TC_2--> Verify Class ID field with numeric inputs.*/
public class GLC_1_2_TC_2 {

	private WebDriver driver;
	private String URL="http://anuttara.in/business/index.php?application=GL";
	private String Exp_msg="New account class has been added";
	
	@BeforeTest
	private void Open() {
		ChromeOptions ch= new ChromeOptions();
		ch.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\Abhishek\\OneDrive\\Desktop\\JAVA_JARS\\driver\\chromedriver.exe");
		driver= new ChromeDriver(ch);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test
	private void Login() throws InterruptedException {
		driver.get(URL);
		Thread.sleep(2000);
		//enter username
		driver.findElement(By.xpath("//input[@name='user_name_entry_field']")).clear();
		driver.findElement(By.xpath("//input[@name='user_name_entry_field']")).sendKeys("abhishek");
		//enter password
		driver.findElement(By.xpath("//input[@name='password']")).clear();
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("abhishek");
		//click login
		driver.findElement(By.xpath("//input[@name='SubmitUser']")).click();
		
		try {
			Alert al= driver.switchTo().alert();
			System.out.println("PopUp: "+al.getText());
			al.accept();
		}
		catch(NoAlertPresentException ex){
			System.out.println(ex);
		}
	}
	
	@Test(dependsOnMethods = {"Login"})
	private void TC_2() throws InterruptedException {
		Thread.sleep(2000);
		//click on Banking & GL module
		driver.findElement(By.xpath("//div[@class='tabs']/a[7]")).click();
		//click on GL Account Classes
		driver.findElement(By.xpath("//div[@id='_page_body']/table/tbody/"
				+ "tr[3]/td/table/tbody/tr[2]/td[2]/a[3]")).click();
		Thread.sleep(2000);
		
		//enter a numeric value in Class ID field
		driver.findElement(By.xpath("//input[@name='id']")).clear();
		driver.findElement(By.xpath("//input[@name='id']")).sendKeys("001");
		
		//enter some dummy value in class name as it is compulsory field to complete the test
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("AAA");
		
		//click on add button
		driver.findElement(By.xpath("//button[@name='ADD_ITEM']")).click();
		Thread.sleep(1000);
		
		String Act_msg= driver.findElement(By.xpath("//div[@id='msgbox']/div")).getText();
		
		try {
			Assert.assertEquals(Act_msg, Exp_msg);
			System.out.println("Expected Message: "+Exp_msg);
			System.out.println("Actual Message: "+Act_msg);
		}
		catch(Exception e) {
			System.out.println("Class ID Rejected: "+e);
		}
	}
	
	@AfterTest
	private void close() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}
}
