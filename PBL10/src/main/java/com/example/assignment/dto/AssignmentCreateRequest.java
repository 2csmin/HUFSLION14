package com.example.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentCreateRequest {

    private String title;
    private String description;

}
