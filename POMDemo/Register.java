package POMDemo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Register {
	
	WebDriver driver =null;
	Properties prop= null;
	HomePage homePage;
	RegisterPage registerPage;
	

	@BeforeMethod
	public void setup() throws Exception
	{

		prop = new Properties();
		prop.load(new FileInputStream(new File("src\\test\\java\\POMDemo\\RegisterProp.properties")));
		String browser = prop.getProperty("browser");
		System.out.println("Broswer : "+ browser);
		String url  = prop.getProperty("url");
		System.out.println("url : "+ url);
		
		ElementUtilities elementRef = new ElementUtilities();
		driver = elementRef.initdriver(browser, url);
		
		homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		homePage.selectRegisterOption();
	}
	
	@AfterMethod
	public void tearDown()
	{
		if(driver!=null)
			driver.quit();
	}
	
	@Test
	public void register() throws Exception
	{
		registerPage = new RegisterPage(driver);
		
		String firstNameText = prop.getProperty("firstName");
		registerPage.firstName(firstNameText);
		
		String lastNameText = prop.getProperty("lastName");
		registerPage.LastName(lastNameText);
		
		
		String emailId = prop.getProperty("emailId");
		String date = timeStamp();
		String randomEmailId = "new"+date+emailId;
		registerPage.emailId(randomEmailId);
		
		String telephoneText = prop.getProperty("telephone");
		registerPage.telephone(telephoneText);
		
		String passwordText = prop.getProperty("password");
		registerPage.password(passwordText);
		Thread.sleep(1000);
		
		String confirmPassword = prop.getProperty("confirmPassword");
		registerPage.confirmPassword(confirmPassword);
		Thread.sleep(1000);
		
		registerPage.clickOnNoButton();
		Thread.sleep(1000);
		registerPage.clickOnCheckbox();
		Thread.sleep(1000);
		driver = registerPage.clickOnContinueButton();
		Thread.sleep(1000);
				
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/h1")).getText(),"Your Account Has Been Created!", "Registration Failed");
	}
	
	public static String timeStamp()
	{
		Date date = new Date();
		String date1= date.toString();
		String date2 = date1.replace(" ", "_").replace(":","_");
		System.out.println("New EmailId : "+date2);
		return date2;
	}
	
}
