package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pages.AdminLoginPage;
import pages.BookingManagmentPage;
import pages.HomePage;

public class TestSuite extends BaseTest {

	@Test()
	public void testUserSignUp() {
		goHome();
		homePage = new HomePage(driver);
		registerPage = homePage.clickSignUp();
		registerPage.fillAllFields();
		accountPage = registerPage.clickSubmit();
		assertTrue(accountPage.isUserInAccountPage());
	}

	@Test(dependsOnMethods = { "testUserSignUp" })
	public void testUserLogin() {
		goHome();
		homePage = new HomePage(driver);
		loginPage = homePage.clickLogin();
		loginPage.addEmailAndPassword();
		accountPage = loginPage.clickLogin();
		assertTrue(accountPage.isUserInAccountPage());
	}

	@Test()
	public void testCreateBooking() {

		goToAdminLoginPage();
		adminLoginPage = new AdminLoginPage(driver);
		adminLoginPage.fillEmailAndPassword();
		adminHomePage = adminLoginPage.clickLogin();
		adminHomePage.clickQuickBook();
		adminHomePage.setTaxAndService();
		quickBookingPage = adminHomePage.clickNext();
		quickBookingPage.fillCustomerData();
		quickBookingPage.fillItemDetails();
		quickBookingPage.fillExtras();
		quickBookingPage.setPaymentType();
		int totalAmount = quickBookingPage.getTotalAmount();
		bookingManagmentPage = quickBookingPage.clickBookNow();
		assertTrue(bookingManagmentPage.isBookingCreated(totalAmount));
	}

	@Test(dependsOnMethods = { "testCreateBooking" })
	public void testDeleteBooking() {
		goToAdminLoginPage();
		adminLoginPage = new AdminLoginPage(driver);
		adminLoginPage.fillEmailAndPassword();
		adminHomePage = adminLoginPage.clickLogin();
		bookingManagmentPage = adminHomePage.clickBookings();
		bookingManagmentPage.searchWithBookingID(BookingManagmentPage.bookingID);
		bookingManagmentPage.deleteBooking();
		assertTrue(bookingManagmentPage.isBookingDeleted(BookingManagmentPage.bookingID));
	}
}
