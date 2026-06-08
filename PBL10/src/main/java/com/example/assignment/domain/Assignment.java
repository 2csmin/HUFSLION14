package com.example.assignment.domain;

import com.example.member.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 생성용 생성자
    public Assignment (String title, String description, Member member) {
        this.title = title;
        this.description = description;
        this.member = member;
    }

    public void updateInfo(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
