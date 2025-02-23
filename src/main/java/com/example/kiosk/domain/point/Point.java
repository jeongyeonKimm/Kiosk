package com.example.kiosk.domain.point;

import com.example.kiosk.domain.BaseEntity;
import com.example.kiosk.domain.member.Member;
import com.example.kiosk.exception.RestApiException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.kiosk.exception.PointErrorCode.INVALID_CHARGE_POINT;

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
            throw new RestApiException(INVALID_CHARGE_POINT);
        }

        this.amount += amount;
    }
}
