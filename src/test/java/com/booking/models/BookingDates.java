package com.booking.models;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDates {
    @SerializedName("checkin")
    private String checkIn;
    @SerializedName("checkout")
    private String checkOut;
}