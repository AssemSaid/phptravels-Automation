package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class BookingManagmentPage extends BasePage {

	private WebDriver driver;
	private By totalLabelInResult = By.xpath("(//tr[@class='xcrud-row xcrud-row-0']//td)[8]");
	private By idLabelInFirstRow = By.xpath("(//tr[@class='xcrud-row xcrud-row-0']//td[@class='xcrud-current'])[1]");
	private By searchButton = By.xpath("//a[contains(text(),'Search')]");
	private By searchDropdown = By.cssSelector("[name='column']");
	private By searchTF = By.cssSelector("[name='phrase']");
	private By goButton = By.xpath("//a[contains(text(),'Go')]");
	private By resultCheckbox = By.cssSelector("td input.checkboxcls");
	private By deleteButton = By.id("deleteAll");
	private By noResultsLabel = By.xpath("//td[contains(text(),'Entries not found.')]");

	private final int WAIT_TIME = 10;
	// public as it will be used in the test case to delete the booking with that
	// id. that's why we can't run the delete without the create TC
	public static int bookingID;

	public BookingManagmentPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isBookingCreated(int expectedAmount) {

		waitUnitElementIsVisible(driver, deleteButton, WAIT_TIME);
		bookingID = getBookingID();
		System.out.println("Booking is created with an ID: " + bookingID);
		searchWithBookingID(bookingID);
		// check the first row as we have just clicked "Book Now" and our booking should
		// be the first in the list
		int actualAmount = (int) Double.parseDouble(driver.findElement(totalLabelInResult).getText());
		if (expectedAmount == actualAmount)
			return true;
		else {
			System.out.println("Booking isn't created!");
			return false;
		}
	}

	private int getBookingID() {
		return Integer.parseInt(driver.findElement(idLabelInFirstRow).getText());
	}

	public void searchWithBookingID(int id) {
		driver.findElement(searchButton).click();
		Select options = new Select(driver.findElement(searchDropdown));
		options.selectByVisibleText("ID");

		driver.findElement(searchTF).sendKeys(Integer.toString(id));
		driver.findElement(goButton).click();
	}

	public void deleteBooking() {
		waitUnitElementIsVisible(driver, resultCheckbox, WAIT_TIME);
		// try/catch doing the same action to avoid the exception
		try {
			driver.findElement(resultCheckbox).click();
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			driver.findElement(resultCheckbox).click();
		}
		driver.findElement(deleteButton).click();
		driver.switchTo().alert().accept();
		System.out.println("Booking with ID: " + bookingID + " is deleted");
	}

	public boolean isBookingDeleted(int id) {
		waitUnitElementIsVisible(driver, searchButton, WAIT_TIME);
		searchWithBookingID(id);

		waitUnitElementIsVisible(driver, noResultsLabel, WAIT_TIME);
		if (driver.getPageSource().contains("Entries not found"))
			return true;
		else
			return false;
	}
}
