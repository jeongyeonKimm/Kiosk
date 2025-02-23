package com.example.kiosk.api.service.point.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PointChargeServiceRequest {

    private Long memberId;
    private Long amount;

    @Builder
    private PointChargeServiceRequest(Long memberId, Long amount) {
        this.memberId = memberId;
        this.amount = amount;
    }
}
