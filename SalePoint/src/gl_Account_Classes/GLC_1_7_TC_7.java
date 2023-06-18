package gl_Account_Classes;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.SalePoint_Login;

/*
 * Verify Class Name field with characters inputs.
 */
public class GLC_1_7_TC_7 extends SalePoint_Login{

	@BeforeMethod
	private void start() throws InterruptedException {
		super.Open();
		super.Login();
		Thread.sleep(2000);
	}
	
	@Test
	private void TC_7() throws InterruptedException {
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
		driver.findElement(By.xpath("//input[@name='id']")).sendKeys("000");
		Thread.sleep(1500);
		
		//enter character value in class name field & click on add button
		driver.findElement(By.xpath("//input[@name='name']")).clear();
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Test7");
		
		driver.findElement(By.xpath("//button[@name='ADD_ITEM']")).click();
		Thread.sleep(2000);
		
		//Actual Error message
		String Act_msg= driver.findElement(By.xpath("//div[@id='msgbox']/div")).getText();
		
		String Exp_MSG="New account class has been added";
		try {
			Assert.assertEquals(Act_msg, Exp_MSG);
			System.out.println("Test Case GLC_1.7_TC_7 Passed ");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	@AfterMethod
	private void Close() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}
}
