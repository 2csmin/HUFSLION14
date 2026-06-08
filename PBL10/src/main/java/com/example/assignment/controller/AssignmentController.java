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
        AssignmentResponse response =
                assignmentService.createAssignment(memberId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/assignments")
    public ResponseEntity<List<AssignmentResponse>> findAllAssignments() {
        return ResponseEntity.ok(assignmentService.findAllAssignments());
    }

    @GetMapping("/members/{memberId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> findAssignmentsByMemberId(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(assignmentService.findByMemberId(memberId));
    }

    @GetMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> findAssignmentById (
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(assignmentService.findById(id));
    }

    @GetMapping("/assignments/search")
    public ResponseEntity<List<AssignmentResponse>> searchAssignments(
            @RequestParam String keyword
    ) {
        return ResponseEntity.ok(assignmentService.searchAssignments(keyword));
    }

    @PutMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> updateAssignment(
            @PathVariable Long id,
            @RequestBody AssignmentUpdateRequest request
    ) {
        return ResponseEntity.ok(
                assignmentService.updateAssignment(id, request)
        );
    }

    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> deleteAssignment(
            @PathVariable Long id
    ) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
