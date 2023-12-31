package com.major.club.domain.user.domain;

import com.major.club.domain.club.domain.Club;
import jakarta.persistence.*;
import leehj050211.bsmOauth.type.BsmUserRole;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private BsmUserRole role;

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

    @ManyToOne
    @JoinColumn(name = "club_id")
    @Setter
    private Club club;

}
