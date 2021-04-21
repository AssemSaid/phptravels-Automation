package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.AccountPage;
import pages.AdminHomePage;
import pages.AdminLoginPage;
import pages.BookingManagmentPage;
import pages.HomePage;
import pages.LoginPage;
import pages.QuickBookingPage;
import pages.RegisterPage;

public class BaseTest {

	protected WebDriver driver;
	protected HomePage homePage;
	protected RegisterPage registerPage;
	protected AccountPage accountPage;
	protected LoginPage loginPage;
	protected AdminLoginPage adminLoginPage;
	protected AdminHomePage adminHomePage;
	protected QuickBookingPage quickBookingPage;
	protected BookingManagmentPage bookingManagmentPage;

	@BeforeMethod
	public void startUp() {
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

	public void goHome() {
		String url = "https://www.phptravels.net/home";
		driver.get(url);
	}

	public void goToAdminLoginPage() {
		String url = "http://www.phptravels.net/admin";
		driver.get(url);
	}

}
