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

    public void addPoints(Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("충전할 포인트는 0보다 커야 합니다.");
        }

        this.amount += amount;
    }
}
