Feature:To verify DeleteBooking API is working fine

Description: To verify Delete Booking API is working fine

Scenario:User is able to Delete booking successfully using DeleteBooking API

    Given User enters valid booking details
    When DeleteBooking API is called
    Then Booking is deleted successfully
