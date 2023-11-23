package com.major.club.domain.application.domain;

import com.major.club.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productName;

    @Column
    private String url;

    @Column
    private int count;

    @Column
    private int price;

    @Column
    private String usage;

    @Column
    private String etc;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
