package ru.gb.api;

import lombok.Data;
import lombok.NoArgsConstructor;


public class OrderDetailsDto {
    private String address;
    private String phone;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
