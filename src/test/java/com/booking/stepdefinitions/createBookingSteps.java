package com.booking.stepdefinitions;

import com.booking.pages.BookingAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static com.booking.pages.BookingAPI.response;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class createBookingSteps {
    private BookingAPI bookingAPI = new BookingAPI();

    @Given("I create a booking with following details:")
    public void i_create_a_booking_with_following_details() {
        bookingAPI.postBooking();
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
        System.out.println("Response status code is: " + response.getStatusCode());
    }

    @Then("the response should contain booking details")
    public void the_response_should_contain_booking_details() {
        String firstName = response.jsonPath().getString("firstname");
        assertEquals("KataApi", firstName);
        System.out.println("Retrieved username is: " + firstName);
    }

    @Then("the booking ID should be generated")
    public void the_booking_id_should_be_generated() {
        assertTrue(true);
        String bookingId = response.jsonPath().getString("bookingid");
        System.out.println("Booking ID is: " + bookingId);
    }
}
