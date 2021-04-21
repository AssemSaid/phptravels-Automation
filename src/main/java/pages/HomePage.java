package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

	private WebDriver driver;
	private By myAccountDropdown = By.xpath("(//a[@id='dropdownCurrency'])[2]");
	private By signupOption = By.xpath("//div/a[contains(text(),'Sign Up')]");
	private By LoginOption = By.xpath("//div/a[contains(text(),'Login')]");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public RegisterPage clickSignUp() {
		clickMyAccount();
		driver.findElement(signupOption).click();
		return new RegisterPage(driver);
	}

	private void clickMyAccount() {
		driver.findElement(myAccountDropdown).click();
	}

	public LoginPage clickLogin() {
		clickMyAccount();
		driver.findElement(LoginOption).click();
		return new LoginPage(driver);
	}

}
