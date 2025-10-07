@GetBooking
Scenario: Get booking by ID
Given a booking exists with ID
When I retrieve the booking by ID
Then the response status code should be 200
And the response should contain correct booking details

@UpdateBooking
Scenario: Update an existing booking
Given a booking exists with ID
When I update the booking with new details:
| firstname | UpdatedName       |
| lastname  | UpdatedLast       |
| email     | updated@gmail.com |
Then the response status code should be 200
And the response should contain updated details

@CancelBooking
Scenario: Delete a booking
Given a booking exists with ID
When I delete the booking
Then the response status code should be 201
And the booking should be deleted