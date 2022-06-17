Feature:To verify UpdateBooking API is working fine

Description: To verify Update Booking API is working fine

Scenario:User is able to update booking successfully using UpdateBooking API

    Given User enters valid booking details
    When UpdateBooking API is called
    Then Booking is updated successfully
