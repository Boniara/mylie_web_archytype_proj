package com.bavuta.model;

/**
 * Created by bogdan on 4/27/2017.
 */
public class Role extends AbstractModel {

    private ROLE role;

    public Role() {
        super();
    }

    public Role(ROLE role) {
        this.role = role;
    }

    public enum ROLE {
        USER_ROLE("USER_ROLE"), ADMIN_ROLE("ADMIN_ROLE");

        private String value;

        ROLE(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }
}
