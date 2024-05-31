package POMDemo;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class Login {
		
	WebDriver driver = null;
	Properties prop;
	
	@BeforeMethod
	public void setup() throws IOException
	{
		prop = new Properties();
		prop.load(new FileInputStream(new File("src/test/java/POMDemo/LoginProp.properties")));
		String browser = prop.getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if(browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if(browser.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		else if(browser.equalsIgnoreCase("safari"))
			driver = new SafariDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(prop.getProperty("url"));
		
		HomePage home = new HomePage(driver);
		driver = home.clickOnMyAccountDropMenu();
		driver = home.selectLoginOption();
		
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		if(driver!=null)
			driver.quit();
	}
	
	@Test
	public void login()
	{
		LoginPage loginPageRef = new LoginPage(driver);
		
		String emailId = prop.getProperty("validEmailId");
		loginPageRef.enterLoginEmailAddress(emailId);
		
		String password = prop.getProperty("validPassword");
		loginPageRef.enterLoginPassword(password);
		
		driver = loginPageRef.clickOnLoginButton();
		
		MyAccountPage myAccountPage = new MyAccountPage(driver);
		
		Assert.assertTrue(myAccountPage.loggedInStatus());
	}
}
