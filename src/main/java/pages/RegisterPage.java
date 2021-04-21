package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

	private WebDriver driver;
	private By firstNameTF = By.cssSelector("[name='firstname']");
	private By lastNameTF = By.cssSelector("[name='lastname']");
	private By phoneTF = By.cssSelector("[name='phone']");
	private By emailTF = By.cssSelector("[name='email']");
	private By passwordTF = By.cssSelector("[name='password']");
	private By confirmPasswordTF = By.cssSelector("[name='confirmpassword']");
	private By submitButton = By.xpath("//div[@class='form-group']/button");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	public void fillAllFields() {
		testData.prepareSignUpData();

		setFirstName();
		setLastName();
		setMobieNumber();
		setEmail();
		setPassword();
		setConfirmPassword();
	}

	public AccountPage clickSubmit() {
		driver.findElement(submitButton).click();
		return new AccountPage(driver);
	}

	private void setFirstName() {
		driver.findElement(firstNameTF).sendKeys(testData.getFirstName());
	}

	private void setLastName() {
		driver.findElement(lastNameTF).sendKeys(testData.getLastName());
	}

	private void setMobieNumber() {
		driver.findElement(phoneTF).sendKeys(Integer.toString(testData.getMobileNumber()));
	}

	private void setEmail() {
		driver.findElement(emailTF).sendKeys(testData.getEmail());
	}

	private void setPassword() {
		driver.findElement(passwordTF).sendKeys(testData.getPassword());
	}

	private void setConfirmPassword() {
		driver.findElement(confirmPasswordTF).sendKeys(testData.getPassword());
	}

}
