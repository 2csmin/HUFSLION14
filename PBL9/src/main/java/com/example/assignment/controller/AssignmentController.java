package com.example.assignment.controller;

import com.example.assignment.domain.Assignment;
import com.example.assignment.dto.AssignmentCreateRequest;
import com.example.assignment.dto.AssignmentResponse;
import com.example.assignment.dto.AssignmentUpdateRequest;
import com.example.assignment.service.AssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping("/members/{memberId}/assignments")
    public ResponseEntity<AssignmentResponse> createAssignment(
            @PathVariable Long memberId,
            @RequestBody AssignmentCreateRequest request
    ) {
        AssignmentResponse response = assignmentService.createAssignment(memberId, request);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/members/{memberId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> findAssignmentsByMemberId(
            @PathVariable Long memberId
    ) {
        List<AssignmentResponse> responses = assignmentService.findByMemberId(memberId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> findAssignmentById (
            @PathVariable Long id
    ) {
        AssignmentResponse response = assignmentService.findById(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> updateAssignment(
            @PathVariable Long id,
            @RequestBody AssignmentUpdateRequest request
    ) {
        AssignmentResponse response = assignmentService.updateAssignment(id, request);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> deleteAssignment(
            @PathVariable Long id
    ) {
        boolean result = assignmentService.deleteAssignment(id);

        if (!result) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
