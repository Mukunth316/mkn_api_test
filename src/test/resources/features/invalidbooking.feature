@CreateBookingInvalidDetails
Feature: User enters Invalid Booking details
  As an user
  I want to perform booking operations
  So that I can manage hotel bookings

  @InvalidData
  Scenario Outline: User should get expected error message when trying to create a booking using invalid data
    Given user enters booking details
      | roomId   | depositPaid   | firstName   | lastName   | email   | phone   | checkIn   | checkOut   |
      | <roomId> | <depositPaid> | <firstName> | <lastName> | <email> | <phone> | <checkIn> | <checkOut> |
    When user sends POST request to create a booking
    Then response code should be 400
    And booking response should contain the error message "<errorMessage>"

    Examples:
      | roomId | depositPaid | firstName           | lastName            | email                     | phone                  | checkIn    | checkOut   | errorMessage                        |
      |        | true        | KataApi             | Automation          | tAutomation0@testmail.com | 985345334903           | 2025-10-31 | 2025-11-15 | must be greater than or equal to 1  |
      | 230    | true        |                     | Automation          | tAutomation0@testmail.com | 985345334903           | 2025-10-31 | 2025-11-15 | Firstname should not be blank       |
      | 231    | false       | KataApiTestsBDDJava | Automation          | tAutomation0@testmail.com | 985345334903           | 2025-10-31 | 2025-11-15 | size must be between 3 and 18       |
      | 232    | true        | ka                  | Automation          | tAutomation0@testmail.com | 985345334903           | 2025-10-31 | 2025-11-15 | size must be between 3 and 18       |
      | 233    | true        | KataApi             |                     | tAutomation0@testmail.com | 985345334903           | 2025-10-31 | 2025-11-15 | Lastname should not be blank        |
      | 234    | true        | KataApi             | KataApiTestsBDDJava | tAutomation0@testmail.com | 985345334903           | 2025-10-31 | 2025-11-15 | size must be between 3 and 18       |
      | 235    | true        | KataApi             | ka                  | tAutomation0@testmail.com | 985345334903           | 2025-10-31 | 2025-11-15 | size must be between 3 and 18       |
      | 236    | true        | KataApi             | Automation          |                           | 985345334903           | 2025-10-31 | 2025-11-15 | must not be null                    |
      | 237    | true        | KataApi             | Automation          | tAutomation0@testmail.com |                        | 2025-10-31 | 2025-11-15 | must not be null                    |
      | 238    | true        | KataApi             | Automation          | tAutomation0@testmail.com | +123456789101187654321 | 2025-10-31 | 2025-11-15 | size must be between 3 and 18       |
      | 239    | true        | KataApi             | Automation          | tAutomation0@testmail.com | +123456789             | 2025-10-31 | 2025-11-15 | size must be between 3 and 18       |
      | 240    | true        | KataApi             | Automation          | tAutomation0@testmail.com | 985345334903           |            | 2025-11-15 | must not be null                    |
      | 241    | true        | KataApi             | Automation          | tAutomation0@testmail.com | 985345334903           | 2025-10-31 |            | must not be null                    |
      | 242    | true        | KataApi             | Automation          | @gmail.com                | 985345334903           | 2025-10-31 | 2025-11-15 | must be a well-formed email address |