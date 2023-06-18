package gl_Account_Classes;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.SalePoint_Login;

/* 
 * GLC_1.4_TC_4Verify Class ID field with special characters inputs.(it should fail)
 * class ID must be numeric
 * */
public class GLC_1_4_TC_4 extends SalePoint_Login{
	
	@BeforeMethod
	private void start() throws InterruptedException {
		super.Open();
		super.Login();
		Thread.sleep(2000);
	}
	
	@Test
	private void TC_4() throws InterruptedException {
		Thread.sleep(2000);
		//click on Banking & GL module
		driver.findElement(By.xpath("//div[@class='tabs']/a[7]")).click();
		
		//click on GL Account Classes
		driver.findElement(By.xpath("//div[@id='_page_body']/table/tbody/"
						+ "tr[3]/td/table/tbody/tr[2]/td[2]/a[3]")).click();
		Thread.sleep(2000);
		
		//Enter a special character value in Class ID field
		driver.findElement(By.xpath("//input[@name='id']")).clear();
		driver.findElement(By.xpath("//input[@name='id']")).sendKeys("$##");
		Thread.sleep(1500);
		
		//Enter some dummy value in class name field as it is compulsory field
		driver.findElement(By.xpath("//input[@name='name']")).clear();
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Test4");
		
		//click on Add button
		driver.findElement(By.xpath("//button[@name='ADD_ITEM']")).click();
		Thread.sleep(2000);
				
		//Actual Error message
		String Act_msg= driver.findElement(By.xpath("//div[@id='msgbox']/div")).getText();
				
		//Expected Error message
		String Exp_ErrMSG="Class ID must be numeric.";
		
		try {
			Assert.assertEquals(Act_msg, Exp_ErrMSG);
		}catch(Exception e) {
			System.out.println("Test Case GLC_1.4_TC_4 Failed "+e);
		}
	}

	@AfterMethod
	private void close() {
		driver.close();
	}
}
