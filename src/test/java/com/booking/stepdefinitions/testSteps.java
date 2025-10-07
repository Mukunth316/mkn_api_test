package com.booking.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.List;
import java.util.Map;

import static com.booking.pages.BookingAPI.response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.json.JSONObject;
import utils.ConfigManager;

public class testSteps {
    private String requestBody;

    @Given("the following booking details:")
    public void givenBookingDetails(DataTable table) {
        Map<String, String> map = table.asMap(String.class, String.class);
        // For nested object like bookingdates, you will need to build JSON structure
        JSONObject json = new JSONObject();
        json.put("roomid", map.get("roomid"));
        json.put("depositpaid", Boolean.valueOf(map.get("depositpaid")));
        json.put("firstname", map.get("firstname"));
        json.put("lastname", map.get("lastname"));
        json.put("email", map.get("email"));
        json.put("phone", map.get("phone"));
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", map.get("checkin"));
        bookingDates.put("checkout", map.get("checkout"));
        json.put("bookingdates", bookingDates);
        this.requestBody = json.toString();
    }

    @When("I send the booking request")
    public void sendBooking() {
        response = given()
                .baseUri("https://automationintesting.online/")
                .basePath("api/booking")
                .contentType(ContentType.JSON)
                .body(this.requestBody)
                .when()
                .log().all()
                .post()
                .thenReturn();
        response.then().statusCode(201);

    }

    @Then("the response body should be:")
    public void verifyResponseBody(DataTable dataTable) {
        List<Map<String, String>> entries = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> entry : entries) {
            System.out.println("Values provided from datatable: " + entry);
            String key = entry.get("field");
            String expectedValue = entry.get("value");
            System.out.println("Expected response values are:" + expectedValue);
            response.then().body(key, equalTo(expectedValue));
        }
    }
}

