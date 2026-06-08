package com.example.assignment.dto;

import com.example.assignment.domain.Assignment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssignmentResponse {

    private Long id;
    private String title;
    private String description;
    private Long memberId;
    private String memberName;

    public static AssignmentResponse from(Assignment assignment) {
        return new AssignmentResponse(
                assignment.getId(),
                assignment.getTitle(),
                assignment.getDescription(),
                assignment.getMember().getId(),
                assignment.getMember().getName()
        );
    }

}
