package gl_Account_Classes;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.SalePoint_Login;

/*
 * verify account name field with Special Characters
 */
public class GLC_1_8_TC_8 extends SalePoint_Login{

	@BeforeMethod
	private void start() throws InterruptedException {
		super.Open();
		super.Login();
		Thread.sleep(2000);
	}
	
	@Test
	private void TC_8() throws InterruptedException {
		Thread.sleep(2000);
		//click on Banking & GL module
		driver.findElement(By.xpath("//div[@class='tabs']/a[7]")).click();
		
		//click on GL Account Classes
		driver.findElement(By.xpath("//div[@id='_page_body']/table/tbody/"
						+ "tr[3]/td/table/tbody/tr[2]/td[2]/a[3]")).click();
		Thread.sleep(2000);
		
		/*Enter a some dummy value in Class ID field as it is compulsory field,
		 *  to complete test process 
		 */
		driver.findElement(By.xpath("//input[@name='id']")).clear();
		driver.findElement(By.xpath("//input[@name='id']")).sendKeys("002");
		Thread.sleep(1500);
		
		//enter numeric value in class name field & click on add button
		driver.findElement(By.xpath("//input[@name='name']")).clear();
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("#$%");
		
		driver.findElement(By.xpath("//button[@name='ADD_ITEM']")).click();
		Thread.sleep(2000);
		
		//Actual Error message
		String Act_msg= driver.findElement(By.xpath("//div[@id='msgbox']/div")).getText();
		
		String Exp_MSG="Enter a valid class name.";
		try {
			Assert.assertEquals(Act_msg, Exp_MSG);
			System.out.println("Test Case GLC_1.8_TC_8 Passed ");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	@AfterMethod
	private void close() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}
}
