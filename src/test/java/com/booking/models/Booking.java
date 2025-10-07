package com.booking.models;

import com.google.gson.annotations.SerializedName;
import io.cucumber.java.it.Data;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @SerializedName("bookingid")
    private int bookingId;

    @SerializedName("roomid")
    private int roomId;

    @SerializedName("firstname")
    private String firstName;

    @SerializedName("lastname")
    private String lastName;

    @SerializedName("depositpaid")
    private boolean depositPaid;

    private String email;

    private String phone;

    @SerializedName("bookingdates")
    private BookingDates bookingDates;

    public Booking(Map<String, String> bookingDetails) {
        this.bookingId = new Random().nextInt(1000);
        this.roomId = Integer.parseInt(bookingDetails.getOrDefault("roomId", "1"));
        this.firstName = bookingDetails.getOrDefault("firstName", "Empty first name");
        this.lastName = bookingDetails.getOrDefault("lastName", "Empty last name");
        this.depositPaid = Boolean.parseBoolean(bookingDetails.getOrDefault("depositPaid", "true"));
        this.email = bookingDetails.getOrDefault("email", "empty@email.com");
        this.phone = bookingDetails.getOrDefault("phone", "+1234567890");
        this.bookingDates = new BookingDates(
                bookingDetails.getOrDefault("checkIn", LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
                bookingDetails.getOrDefault("checkOut", LocalDateTime.now().plusDays(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        );
    }

}
