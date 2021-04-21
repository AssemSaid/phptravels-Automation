package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	private WebDriver driver;
	private By emailTF = By.cssSelector("[name='username']");
	private By passwordTF = By.cssSelector("[name='password']");
	private By loginBtn = By.xpath("//button[text()='Login']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void addEmailAndPassword() {
		testData.prepareSignUpData();

		setEmail();
		setPassword();
	}

	public AccountPage clickLogin() {
		driver.findElement(loginBtn).click();
		return new AccountPage(driver);
	}

	private void setEmail() {
		driver.findElement(emailTF).sendKeys(testData.getEmail());
	}

	private void setPassword() {
		driver.findElement(passwordTF).sendKeys(testData.getPassword());
	}

}
