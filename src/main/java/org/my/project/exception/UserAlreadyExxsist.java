package org.my.project.exception;

public class UserAlreadyExxsist extends RuntimeException {
    public UserAlreadyExxsist(String message) {
        super(message);
    }
}
