package com.moweez.moweez_backend.entity;

import java.time.LocalDateTime;

/**
 * A common interface for all application users, such as customers and service providers.
 * This interface defines the basic properties required for authentication and user management.
 */
public interface AppUser {

    /**
     * Returns the unique identifier of the user.
     *
     * @return the user ID.
     */
    Long getId();

    /**
     * Returns the user's first name.
     *
     * @return the first name.
     */
    String getFirstName();

    /**
     * Returns the user's last name.
     *
     * @return the last name.
     */
    String getLastName();

    /**
     * Returns the user's email address.
     * This is typically used as the username for authentication.
     *
     * @return the email address.
     */
    String getEmail();

    /**
     * Returns the user's password.
     * This should be the hashed version of the password.
     *
     * @return the hashed password.
     */
    String getPassword();

    /**
     * Indicates whether the user's account is enabled.
     *
     * @return true if the account is enabled, false otherwise.
     */
    boolean isEnabled();

    /**
     * Returns the role assigned to the user.
     * This is used to determine access rights within the application.
     *
     * @return the user's role.
     */
    Role getRole();

    /**
     * Returns the timestamp when the user account was created.
     *
     * @return the creation date.
     */
    LocalDateTime getCreatedDate();

    /**
     * Returns the timestamp when the user account was last modified.
     *
     * @return the last modified date.
     */
    LocalDateTime getLastModifiedDate();
}
