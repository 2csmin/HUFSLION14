package com.example.dto;

import com.example.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponse {

    private Long id;
    private String name;
    private String major;
    private int generation;
    private String part;
    private String roleName;
    private String studentId;
    private String postition;

    public static MemberResponse from(Member member) {
        return new MemberResponse(
            member.getId(),
            member.getName(),
            member.getMajor(),
            member.getGeneration(),
            member.getPart(),
            member.getRoleType().getDisplayName(),
            member.getStudentId(),
            member.getPosition()
        );
    }
}
