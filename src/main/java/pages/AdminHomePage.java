package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AdminHomePage extends BasePage {

	private WebDriver driver;
	private By quickBookButton = By.cssSelector("[data-toggle='modal']");
	private By taxDropdown = By.id("apply");
	private By serviceDropdown = By.id("servicetype");
	private By nextButton = By.xpath("//button[contains(text(),'Next')]");
	private By bookingsButton = By.cssSelector("[action='https://www.phptravels.net/admin/bookings/'] button");

	private final int WAIT_TIME = 15;

	public AdminHomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickQuickBook() {
		waitUnitElementIsVisible(driver, quickBookButton, WAIT_TIME);
		driver.findElement(quickBookButton).click();
	}

	public BookingManagmentPage clickBookings() {
		waitUnitElementIsVisible(driver, bookingsButton, WAIT_TIME);
		driver.findElement(bookingsButton).click();
		return new BookingManagmentPage(driver);
	}

	public void setTaxAndService() {
		testData.prepareBookingData();

		Select options = new Select(driver.findElement(taxDropdown));
		if (testData.getIsTaxApplied() == false)
			options.selectByVisibleText("No");
		// It's always Tours because there is no other option and it's a mandatory field
		options = new Select(driver.findElement(serviceDropdown));
		options.selectByVisibleText("Tours");
	}

	public QuickBookingPage clickNext() {
		driver.findElement(nextButton).click();
		return new QuickBookingPage(driver);
	}
}
