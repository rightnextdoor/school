package com.school.roster.payload;

import com.school.roster.model.PhoneNumberStudent;
import com.school.roster.model.User;

public class PhoneRequest {
    private User user;
    private PhoneNumberStudent phoneNumber;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PhoneNumberStudent getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumberStudent phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
