package com.pluralsight.conference.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;

public class Account {
    @Null
    @NotEmpty
    private String userName;
    @Null
    @NotEmpty
    private String firstName;
    @Null
    @NotEmpty
    private String lastName;
    @Null
    @NotEmpty
    private String email;
    @Null
    @NotEmpty
    private String password;
    private String matchingPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
