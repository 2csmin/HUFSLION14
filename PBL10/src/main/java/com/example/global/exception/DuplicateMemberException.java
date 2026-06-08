package com.example.global.exception;

public class DuplicateMemberException extends RuntimeException {
    public DuplicateMemberException(String message) {
        super(message);
    }
}
