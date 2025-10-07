package com.booking.stepdefinitions;

import com.booking.models.Booking;
import com.booking.pages.BookingAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import static com.booking.pages.BookingAPI.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;


public class commonStepDefs {

    private BookingAPI bookingAPI = new BookingAPI();

    @Given("user enters booking details")
    public void user_enters_booking_details(io.cucumber.datatable.DataTable dataTable) {
        bookingAPI.invalidPost();
        //bookingAPI.setBookingDetails(new Booking(dataTable.asMaps().get(0)));
    }

    @When("user sends POST request to create a booking")
    public void user_sends_post_request_to_create_a_booking() {

    }

    @Then("response code should be {int}")
    public void response_code_should_be(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
        System.out.println("Response status code is: " + response.getStatusCode());
    }

    @Then("booking response should contain the error message {string}")
    public void booking_response_should_contain_the_error_message(String expectedErrors) {
        String expectedErrors1 = "size must be between 3 and 18/Firstname should not be blank/must be greater than or equal to 1";
        List<String> expectedErrorMessages = Arrays.stream(expectedErrors1.split("/"))
                .map(String::trim)
                .collect(Collectors.toList());

        List<String> actualErrorMessages = response.jsonPath().getList("errors", String.class);

        assertNotNull(actualErrorMessages, "errors array should not be null");
        assertEquals(expectedErrorMessages.size(), actualErrorMessages.size(),
                "Number of error messages should match");

        // Sort both lists and then compare — good way if order isn't guaranteed
        List<String> sortedExpected = expectedErrorMessages.stream().sorted().collect(Collectors.toList());
        List<String> sortedActual = actualErrorMessages.stream().sorted().collect(Collectors.toList());

        assertIterableEquals(sortedExpected, sortedActual,
                "Error messages should match ignoring order");
    }

    @Then("the response should contain correct booking details")
    public void verifyCorrectBookingDetails() {
        assertEquals("KataApi", response.jsonPath().getString("firstname"));
        assertEquals("Automation", response.jsonPath().getString("lastname"));
    }

    @Then("the response should contain updated details")
    public void verifyUpdatedDetails() {
        assertEquals("UpdatedName", response.jsonPath().getString("firstname"));
        assertEquals("UpdatedLast", response.jsonPath().getString("lastname"));
    }

    @Then("the booking should be deleted")
    public void verifyBookingDeleted() {
        Response getResponse = bookingAPI.getBooking(bookingId);
        assertEquals(404, getResponse.getStatusCode());
    }

    @Given("a booking exists with ID")
    public void bookingExists() {
        if (bookingId == 0) {
            // Create a booking first if none exists
            response = bookingAPI.createBooking("KataApi", "Automation", "apikata@gmail.com", "+23423252623", "2025-10-20", "2025-10-21", false, 2);
            bookingId = response.jsonPath().getInt("bookingid");
        }
    }

    @When("I retrieve the booking by ID")
    public void retrieveBooking() {
        response = bookingAPI.getBooking(bookingId);
    }

    @When("I update the booking with new details:")
    public void updateBooking(DataTable dataTable) {
        var updateData = dataTable.asMap(String.class, String.class);
        response = bookingAPI.updateBooking(bookingId, updateData.get("firstname"), updateData.get("lastname"), updateData.get("email"));
    }

    @When("I delete the booking")
    public void deleteBooking() {
        response = bookingAPI.deleteBooking(bookingId);
    }
}