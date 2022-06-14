package com.leafbound.models;

import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private int roleId;

    public UserDTO() {
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param password
     * @param email
     * @param roleId
     * @param settingsId
     */
    public UserDTO(String firstName, String lastName, String password, String email, int roleId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
    }

    /**
     * @param id
     * @param firstName
     * @param lastName
     * @param password
     * @param email
     * @param roleId
     * @param settingsId
     */
    public UserDTO(UUID id, String firstName, String lastName, String password, String email, int roleId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
    }

    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserDTO [email=" + email + ", firstName=" + firstName + ", id=" + id + ", lastName=" + lastName
                + ", roleId=" + roleId + "]";
    }

}
