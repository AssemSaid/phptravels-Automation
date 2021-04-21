package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int mobileNumber;

	private String adminMail;
	private String adminPassword;
	private String isTaxApplied;
	private String paymentType;
	private String isCustomerRegistered;
	private String customerName;
	private String tourName;
	private String returnAirTicket;
	private String threeNights;
	private String travelInsurance;
	private String airportPickup;
	private String date;
	private int noOfAdults;
	private int noOfChilds;
	private int noOfInfants;

	private final int SIGNUP_COLUMNS_NUMBER = 4;
	private final int BOOKING_COLUMNS_NUMBER = 14;
	private final int BOOKING_LAST_STRING_COLUMN_INDEX = 5;
	private final int BOOKING_ADULTS_COLUMN_INDEX = 12;
	private final int BOOKING_CHLIDS_COLUMN_INDEX = 13;
	private final int BOOKING_INFANTS_COLUMN_INDEX = 14;

	private final int ROW_TO_READ = 1;
	private String[] signupDataArray = new String[SIGNUP_COLUMNS_NUMBER];
	private String[] bookingDataArray = new String[BOOKING_COLUMNS_NUMBER];

	public void prepareSignUpData() {

		String excelPath = "./TestData/Data.xlsx";
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(excelPath);
			// first sheet
			XSSFSheet sheet = workbook.getSheetAt(0);

			for (int i = 0; i <= SIGNUP_COLUMNS_NUMBER; i++) {
				if (i == SIGNUP_COLUMNS_NUMBER)
					mobileNumber = (int) sheet.getRow(ROW_TO_READ).getCell(i).getNumericCellValue();
				else
					signupDataArray[i] = sheet.getRow(ROW_TO_READ).getCell(i).getStringCellValue();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		firstName = signupDataArray[0];
		lastName = signupDataArray[1];
		email = signupDataArray[2];
		password = signupDataArray[3];
	}

	public void prepareBookingData() {

		String excelPath = "./TestData/Data.xlsx";
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(excelPath);
			// second sheet
			XSSFSheet sheet = workbook.getSheetAt(1);

			for (int i = 0; i <= BOOKING_COLUMNS_NUMBER; i++) {
				if (i == BOOKING_ADULTS_COLUMN_INDEX)
					noOfAdults = (int) sheet.getRow(ROW_TO_READ).getCell(i).getNumericCellValue();
				else if (i == BOOKING_CHLIDS_COLUMN_INDEX)
					noOfChilds = (int) sheet.getRow(ROW_TO_READ).getCell(i).getNumericCellValue();
				else if (i == BOOKING_INFANTS_COLUMN_INDEX)
					noOfInfants = (int) sheet.getRow(ROW_TO_READ).getCell(i).getNumericCellValue();
				else if (i <= BOOKING_LAST_STRING_COLUMN_INDEX)
					bookingDataArray[i] = sheet.getRow(ROW_TO_READ).getCell(i).getStringCellValue();
				else
					bookingDataArray[i] = Boolean.toString(sheet.getRow(ROW_TO_READ).getCell(i).getBooleanCellValue());
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		adminMail = bookingDataArray[0];
		adminPassword = bookingDataArray[1];
		paymentType = bookingDataArray[2];
		tourName = bookingDataArray[3];
		customerName = bookingDataArray[4];
		date = bookingDataArray[5];
		isTaxApplied = bookingDataArray[6];
		isCustomerRegistered = bookingDataArray[7];
		returnAirTicket = bookingDataArray[8];
		threeNights = bookingDataArray[9];
		travelInsurance = bookingDataArray[10];
		airportPickup = bookingDataArray[11];
	}

	public String getAdminMail() {
		return adminMail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public boolean getIsTaxApplied() {
		return Boolean.parseBoolean(isTaxApplied);
	}

	public String getPaymentType() {
		return paymentType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getTourName() {
		return tourName;
	}

	public boolean getIsCustomerRegistered() {
		return Boolean.parseBoolean(isCustomerRegistered);
	}

	public boolean getReturnAirTicket() {
		return Boolean.parseBoolean(returnAirTicket);
	}

	public boolean getThreeNights() {
		return Boolean.parseBoolean(threeNights);
	}

	public boolean getTravelInsurance() {
		return Boolean.parseBoolean(travelInsurance);
	}

	public boolean getAirportPickup() {
		return Boolean.parseBoolean(airportPickup);
	}

	public int getNoOfAdults() {
		return noOfAdults;
	}

	public int getNoOfChilds() {
		return noOfChilds;
	}

	public int getNoOfInfants() {
		return noOfInfants;
	}

	public String getDate() {
		return date;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}
}
