@CreateBooking
Feature: Booking API Operations
  As an user
  I want to perform booking operations
  So that I can manage hotel bookings

  @CreateBookingValid
  Scenario: Create a new booking with valid data
    Given I create a booking with following details:
    Then the response status code should be 201
    And the response should contain booking details
    And the booking ID should be generated
