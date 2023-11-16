package com.major.club.domain.report.domain;

import com.major.club.domain.club.domain.Club;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String subject;

    @Column
    @ElementCollection
    private Set<String> goals;

    @Column
    @ElementCollection
    private Set<String> techStacks;

    @Column
    private String progress;

    @Column
    private String solutions;

    @Column
    private String feedbackFromMentor;

    @Column
    private String feedbackFromTeacher;

    @ManyToOne
    @JoinColumn(name = "id")
    private Club club;

}
