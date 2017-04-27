package com.bavuta.model.models;

import com.bavuta.model.AbstractModel;

/**
 * Created by bogdan on 4/27/2017.
 */
public class User extends AbstractModel {

    private String firstName;
    private String lastName;
    private String email;

    public User() {
        super();
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
}
