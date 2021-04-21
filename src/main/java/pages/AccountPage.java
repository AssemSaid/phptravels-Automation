package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {

	private WebDriver driver;
	private final int WAIT_TIME = 10;
	private By welcomeLabel = By.xpath("//*[contains(text(),'Hi, ')]");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isUserInAccountPage() {
		waitUnitElementIsVisible(driver, welcomeLabel, WAIT_TIME);
		return driver.findElement(welcomeLabel).isDisplayed();
	}

}
