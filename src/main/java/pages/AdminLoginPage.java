package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.TestData;

public class AdminLoginPage extends BasePage {

	private WebDriver driver;
	private TestData testData = new TestData();
	private By emailTF = By.xpath("//label/input[@placeholder='Email']");
	private By passwordTF = By.xpath("//label/input[@placeholder='Password']");
	private By loginButton = By.cssSelector("[type='submit']");

	public AdminLoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void fillEmailAndPassword() {
		testData.prepareBookingData();
		setEmail(testData.getAdminMail());
		setPassword(testData.getAdminPassword());
	}

	public AdminHomePage clickLogin() {
		driver.findElement(loginButton).click();
		return new AdminHomePage(driver);
	}

	private void setEmail(String email) {
		driver.findElement(emailTF).sendKeys(email);
	}

	private void setPassword(String password) {
		driver.findElement(passwordTF).sendKeys(password);
	}

}
