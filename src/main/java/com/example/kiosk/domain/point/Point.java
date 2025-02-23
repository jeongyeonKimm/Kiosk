package com.example.kiosk.domain.point;

import com.example.kiosk.domain.BaseEntity;
import com.example.kiosk.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Point extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private Long amount;

    @Builder
    private Point(Member member, Long amount) {
        this.member = member;
        this.amount = amount;
    }
}
