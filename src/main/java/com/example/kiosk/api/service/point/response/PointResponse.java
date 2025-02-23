package com.example.kiosk.api.service.point.response;

import com.example.kiosk.domain.member.Member;
import com.example.kiosk.domain.point.Point;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PointResponse {

    private Long id;
    private Long memberId;
    private Long amount;

    @Builder
    private PointResponse(Long id, Long memberId, Long amount) {
        this.id = id;
        this.memberId = memberId;
        this.amount = amount;
    }

    public static PointResponse of(Member member) {
        Point point = member.getPoint();
        return PointResponse.builder()
                .id(point.getId())
                .memberId(member.getId())
                .amount(point.getAmount())
                .build();
    }
}
