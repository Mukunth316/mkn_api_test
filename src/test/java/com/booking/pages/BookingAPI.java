package com.booking.pages;

import com.booking.models.Booking;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import utils.ConfigManager;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingAPI {

    public static Response response;
    public static int bookingId;
    private String baseURI;
    private String token;
    private static BookingAPI bookingAPI;

    public static Response resp;

    @Getter
    @Setter
    private Booking bookingDetails;

    public static synchronized BookingAPI getBookingAPIInstance() {
        if (bookingAPI == null) {
            bookingAPI = new BookingAPI();
        }

        return bookingAPI;
    }

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
        RestAssured.baseURI = baseURI;
        RestAssured.useRelaxedHTTPSValidation();
    }

    public Response invalidPost() {
        response = given()
                .baseUri("https://automationintesting.online/")
                .basePath("api/booking")
                .contentType(ContentType.JSON)
                .body(ConfigManager.getInvalidJSONAsString())
                .when()
                .log().all()
                .post()
                .thenReturn();
        response.then().statusCode(400);
        return response;

        /*Map<String, String> bookingDetail = new HashMap<>();
        bookingDetail.put("firstname", bookingDetail.get("firstName"));
        bookingDetail.put("lastname", bookingDetail.get("lastName"));
        bookingDetail.put("email", bookingDetail.get("email"));
        bookingDetail.put("phone", bookingDetail.get("phone"));
        bookingDetail.put("roomid", bookingDetail.get("roomId"));
        bookingDetail.put("depositPaid", bookingDetail.get("depositPaid"));
        bookingDetail.put("checkin", bookingDetail.get("2025-10-20"));
        bookingDetail.put("checkout", bookingDetail.get("2025-10-21"));

        RestAssured.baseURI = ConfigManager.getBaseURI();
        RequestSpecification request = RestAssured.given();
        Response resp = RestAssured.given()
                .contentType(ContentType.JSON).log().all()
                .body(bookingDetail)
                .when()
                .post("api/booking");
        assertEquals(400, resp.getStatusCode());
        assertEquals(resp.getBody().prettyPrint(), resp.getBody().asString());
        System.out.println(resp.getBody().prettyPrint());
        System.out.println("Response status code is: " + resp.getStatusCode());
        return resp;*/
    }

    private String getAuthToken() {
        if (token == null) {
            Map<String, String> auth = new HashMap<>();
            auth.put("username", "admin");
            auth.put("password", "password");

            Response response = RestAssured.given()
                    .contentType(ContentType.JSON).log().all()
                    .body(auth)
                    .when()
                    .post("api/auth/login");

            token = response.jsonPath().getString("token");
        }
        return token;
    }

    public Response postBooking() {
        response = given()
                .baseUri("https://automationintesting.online/")
                .basePath("api/booking")
                .contentType(ContentType.JSON)
                .body(ConfigManager.getJSONAsString())
                .when()
                .log().all()
                .post()
                .thenReturn();
        response.then().statusCode(201);
        return response;
    }

    public Response createBooking(String firstname, String lastname, String email,
                                  String phone, String checkin, String checkout, boolean deposit, int roomid) {

        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);

        Map<String, Object> booking = new HashMap<>();
        booking.put("firstname", firstname);
        booking.put("lastname", lastname);
        booking.put("email", email);
        booking.put("phone", phone);
        booking.put("bookingdates", bookingDates);
        booking.put("depositpaid", deposit);
        booking.put("roomid", roomid);

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .headers("Content-Type", "application/json")
                .body(booking)
                .when()
                .log().all()
                .post("api/booking/");

    }

    public Response getBooking(int bookingId) {
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + getAuthToken())
                .when()
                .get("api/booking/" + bookingId);
    }

    public Response updateBooking(int bookingId, String firstname, String lastname, String email) {
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("firstname", firstname);
        updateData.put("lastname", lastname);
        updateData.put("email", email);

        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + getAuthToken())
                .body(updateData)
                .when()
                .put("api/booking/" + bookingId);
    }

    public Response deleteBooking(int bookingId) {
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + getAuthToken())
                .when()
                .delete("api/booking/" + bookingId);
    }
}