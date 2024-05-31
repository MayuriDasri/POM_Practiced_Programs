package POMDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
	
	WebDriver driver = null;
	private WebElement firstNameInput;
	private WebElement lastNameInput;
	private WebElement emailIdInput;
	private WebElement telephoneInput;
	private WebElement passwordInput;
	private WebElement confirmPasswordInput;
	private WebElement noButtonOption;
	private WebElement checkBoxClick;
	private WebElement continueButton;
	
	
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void firstName(String firstNameText)
	{
		firstNameInput = driver.findElement(By.id("input-firstname"));
		firstNameInput.sendKeys(firstNameText);
	}
	public void LastName(String lastNameText)
	{
		lastNameInput = driver.findElement(By.id("input-lastname"));
		lastNameInput.sendKeys(lastNameText);
	}
	public void emailId(String newEmailIdText)
	{
		emailIdInput = driver.findElement(By.id("input-email"));
		emailIdInput.sendKeys(newEmailIdText);
	}
	public void telephone(String telephoneText)
	{
		telephoneInput = driver.findElement(By.id("input-telephone"));
		telephoneInput.sendKeys(telephoneText);
	}
	public void password(String passwordText)
	{
		passwordInput = driver.findElement(By.id("input-password"));
		passwordInput.sendKeys(passwordText);
	}
	public void confirmPassword(String confirmPasswordText)
	{
		confirmPasswordInput = driver.findElement(By.id("input-confirm"));
		confirmPasswordInput.sendKeys(confirmPasswordText);
	}
	public void clickOnNoButton()
	{
		noButtonOption = driver.findElement(By.xpath("//input[@value='0']"));
		noButtonOption.click();
	}
	public void clickOnCheckbox()
	{
		checkBoxClick = driver.findElement(By.xpath("//input[@name='agree']"));
		checkBoxClick.click();
	}
	public WebDriver clickOnContinueButton()
	{
		 continueButton = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
		 continueButton.click();
		 return driver;
	}
}
