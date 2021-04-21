package pages;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class QuickBookingPage extends BasePage {

	private WebDriver driver;
	private By userTypeDropdown = By.id("selusertype");
	private By guestFirstNameTF = By.id("fname");
	private By guestLastNameTF = By.id("lname");
	private By guestMobileTF = By.id("mobile");
	private By guestEmailTF = By.id("email");
	private By dateTF = By.cssSelector(".col-md-3 [name ='checkin']");
	private By adultsDropdown = By.id("adults");
	private By childsDropdown = By.id("children");
	private By infantsDropdown = By.id("infants");
	private By returnAireTicketsCheckbox = By.xpath("(//input[@name='extras[]'])[1]");
	private By threeNightsCheckbox = By.xpath("(//input[@name='extras[]'])[2]");
	private By travelInsuracneCheckbox = By.xpath("(//input[@name='extras[]'])[3]");
	private By airportPickupCheckbox = By.xpath("(//input[@name='extras[]'])[4]");
	private By paymentTypeDropdown = By.cssSelector("[name='paymethod']");
	private By bookNowButton = By.cssSelector("[type='submit']");
	private By grandtotalLabel = By.id("grandtotal");
	private By customerNameDropdown = By.xpath("(//a[@class='select2-choice'])[1]");
	private By tourNameDropdown = By.xpath("(//a[@class='select2-choice'])[2]");

	private String guestFirstName;
	private String guestLastName;

	private final int STRING_LENGTH = 6;
	private final int MIN_INTEGER = 1000;
	private final int MAX_INTEGER = 6000;
	private final int WAIT_TIME = 10;

	public QuickBookingPage(WebDriver driver) {
		this.driver = driver;
	}

	public void fillCustomerData() {
		testData.prepareBookingData();

		if (testData.getIsCustomerRegistered() == false) {
			guestFirstName = generateName(STRING_LENGTH);
			guestLastName = generateName(STRING_LENGTH);
			String email = generateMail(guestFirstName, MIN_INTEGER, MAX_INTEGER);
			int mobileNumber = generateNumber(MIN_INTEGER, MAX_INTEGER);

			Select options = new Select(driver.findElement(userTypeDropdown));
			options.selectByVisibleText("Gusest");

			setFirstName(guestFirstName);
			setLastName(guestLastName);
			setEmail(email);
			setMobileNumber(mobileNumber);
		} else {
			driver.findElement(customerNameDropdown).click();
			By customerNameOption = By.xpath("//div[contains(text(),'" + testData.getCustomerName() + "')]");
			waitUnitElementIsVisible(driver, customerNameOption, 10);
			driver.findElement(customerNameOption).click();
		}
	}

	public void fillItemDetails() {
		testData.prepareBookingData();

		setDate(testData.getDate());
		setTourName(testData.getTourName());

		waitUnitElementIsVisible(driver, adultsDropdown, WAIT_TIME);

		setNoOfAdults(testData.getNoOfAdults());
		setNoOfChilds(testData.getNoOfChilds());
		setNoOfInfants(testData.getNoOfInfants());
	}

	public void fillExtras() {
		testData.prepareBookingData();

		if (testData.getReturnAirTicket() == true)
			checkFlag(returnAireTicketsCheckbox);
		if (testData.getThreeNights() == true)
			checkFlag(threeNightsCheckbox);
		if (testData.getTravelInsurance() == true)
			checkFlag(travelInsuracneCheckbox);
		if (testData.getAirportPickup() == true)
			checkFlag(airportPickupCheckbox);
	}

	public void setPaymentType() {
		testData.prepareBookingData();

		Select options = new Select(driver.findElement(paymentTypeDropdown));
		options.selectByVisibleText(testData.getPaymentType());
	}

	public BookingManagmentPage clickBookNow() {
		driver.findElement(bookNowButton).click();
		return new BookingManagmentPage(driver);
	}

	public int getTotalAmount() {
		String totalAmount = driver.findElement(grandtotalLabel).getText();
		return (int) Double.parseDouble(totalAmount.substring(1));
	}

	public String getGuestFirstName() {
		return guestFirstName;
	}

	public String getGuestLastName() {
		return guestLastName;
	}

	private void checkFlag(By locator) {
		driver.findElement(locator).click();
	}

	private void setDate(String date) {
		driver.findElement(dateTF).sendKeys(date);
	}

	private void setTourName(String tourName) {
		driver.findElement(tourNameDropdown).click();
		By tourNameOption = By.xpath("//div[contains(text(),'" + testData.getTourName() + "')]");
		waitUnitElementIsVisible(driver, tourNameOption, 10);
		driver.findElement(tourNameOption).click();
	}

	private void setNoOfAdults(int NoOfAdults) {
		if (NoOfAdults <= 6 || NoOfAdults > 0) {
			Select options = new Select(driver.findElement(adultsDropdown));
			options.selectByVisibleText(Integer.toString(NoOfAdults));
		} else
			System.out.println("No. of Audlts should be between 1 and 6!");
	}

	private void setNoOfChilds(int NoOfChilds) {
		if (NoOfChilds <= 4 || NoOfChilds > 0) {
			Select options = new Select(driver.findElement(childsDropdown));
			options.selectByVisibleText(Integer.toString(NoOfChilds));
		} else
			System.out.println("No. of Childs should be between 1 and 4!");
	}

	private void setNoOfInfants(int NoOfInfants) {
		if (NoOfInfants <= 2 || NoOfInfants > 1) {
			Select options = new Select(driver.findElement(infantsDropdown));
			options.selectByVisibleText(Integer.toString(NoOfInfants));
		} else
			System.out.println("No. of Childs should be between 1 and 4!");
	}

	private void setFirstName(String firstName) {
		driver.findElement(guestFirstNameTF).sendKeys(firstName);
	}

	private void setLastName(String lastName) {
		driver.findElement(guestLastNameTF).sendKeys(lastName);
	}

	private void setEmail(String email) {
		driver.findElement(guestEmailTF).sendKeys(email);
	}

	private void setMobileNumber(int mobileNumber) {
		driver.findElement(guestMobileTF).sendKeys(Integer.toString(mobileNumber));
	}

	private String generateName(int length) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			sb.append(randomChar);
		}
		return sb.toString();
	}

	private String generateMail(String name, int minNum, int maxNum) {
		return name + generateNumber(minNum, maxNum) + "@gmail.com";
	}

	private int generateNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
}
