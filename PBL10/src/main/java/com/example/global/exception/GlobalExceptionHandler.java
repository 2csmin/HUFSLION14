package com.example.global.exception;

import com.example.global.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotFound(MemberNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, e.getMessage()));
    }

    @ExceptionHandler(AssignmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAssignmentNotFound(AssignmentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, e.getMessage()));
    }

    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateMember(DuplicateMemberException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(409, e.getMessage()));
    }
}
