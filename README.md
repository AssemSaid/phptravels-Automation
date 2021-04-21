# phptravels-Automation
- This is project represents 4 testing scenarios for some user actions on 'phptravels' website
- It's a Maven Java project using Data Driven, TestNG frameworks and POM design pattern

## Notes
- There are 4 test methods:
1. testUserSignUp: To test sign up functionality using testdata from an external Excel file
2. testUserLogin: To test login functionality (it depends on testUserSignUp as we login with the credentials we entered while creating the account)
3. testCreateBooking: To test creating booking tour (all booking data and logged in user info. are gotten from the same external Excel file (different sheet)
4. testDeleteBooking: To test booking deletion (in this test we delete the booking created in testCreateBooking so it depends on running testCreateBooking first)
- Driver: Chrome version 90
