Feature:To verify CreateBooking API is working fine

Description: To verify Create Booking API is working fine

Scenario:User is able to book rooms successfully using CreateBooking API

    Given User enters valid details
    When CreateBooking API is called
    Then Booking is created successfully
    
    