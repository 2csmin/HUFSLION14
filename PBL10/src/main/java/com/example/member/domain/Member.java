package com.example.member.domain;

import com.example.assignment.domain.Assignment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String major;
    private String part;
    private int generation;
    private String studentId;
    private String position;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @OneToMany(mappedBy = "member")
    private List<Assignment> assignments = new ArrayList<>();

    public Member(String name, String major, int generation, String part, RoleType roleType, String studentId, String position) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.roleType = roleType;
        this.studentId = studentId;
        this.position = position;
    }

    public void updateInfo(String major, int generation, String part) {
        this.major = major;
        this.generation = generation;
        this.part = part;
    }

    public void updateStudentId(String studentId) {
        this.studentId = studentId;
        this.position = null;
        this.roleType = RoleType.LION;
    }

    public void updatePosition(String position) {
        this.position = position;
        this.studentId = null;
        this.roleType = RoleType.STAFF;
    }
}
