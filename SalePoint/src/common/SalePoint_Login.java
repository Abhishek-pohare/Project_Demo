package common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SalePoint_Login {

	public WebDriver driver;
	public String URL="http://anuttara.in/business/index.php?application=GL";
	
	//Browser invoke 
	public void Open() {
		ChromeOptions ch= new ChromeOptions();
		ch.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\Abhishek\\OneDrive\\Desktop\\JAVA_JARS\\driver\\chromedriver.exe");
		driver= new ChromeDriver(ch);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	//Login to sale point
	public void Login() throws InterruptedException {
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
}
