package com.example.kiosk.domain.member;

import com.example.kiosk.domain.point.Point;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "member", cascade = ALL, orphanRemoval = true, fetch = LAZY)
    private Point point;

    @Builder
    private Member(String name) {
        this.name = name;
    }
}
