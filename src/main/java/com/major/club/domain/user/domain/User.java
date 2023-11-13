package com.major.club.domain.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Authority authority;

    @Column
    private int studentNumber;

    @Column
    private int grade;

    @Column
    private int classNumber;

    @Column
    private boolean isGraduated;

    @Column
    private String email;

    @Builder
    public User(String name, Authority authority, int studentNumber, int grade, int classNumber, boolean isGraduated, String email) {
        this.name = name;
        this.authority = authority;
        this.studentNumber = studentNumber;
        this.grade = grade;
        this.classNumber = classNumber;
        this.isGraduated = isGraduated;
        this.email = email;
    }
}
