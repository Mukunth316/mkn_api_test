Feature: Booking API

  Scenario: Create booking with DataTable
    Given the following booking details:
      | field       | value            |
      | roomid      | 117              |
      | depositpaid | true             |
      | firstname   | John             |
      | lastname    | Doe              |
      | email       | john@example.com |
      | phone       | +1234567890      |
      | checkin     | 2025-10-20       |
      | checkout    | 2025-10-21       |
    When I send the booking request
    Then the response body should be:
      | field     | value |
      | firstname | John  |
      | lastname  | Doe   |

