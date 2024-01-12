package com.school.roster.payload;

import com.school.roster.model.AddressStudent;
import com.school.roster.model.User;

public class AddressRequest {
    private User user;
    private AddressStudent address;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AddressStudent getAddress() {
        return address;
    }

    public void setAddress(AddressStudent address) {
        this.address = address;
    }
}
