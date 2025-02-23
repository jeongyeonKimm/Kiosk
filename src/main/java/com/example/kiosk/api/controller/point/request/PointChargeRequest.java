package com.example.kiosk.api.controller.point.request;

import com.example.kiosk.api.service.point.request.PointChargeServiceRequest;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PointChargeRequest {

    private Long memberId;

    @Positive(message = "충전할 금액은 0보다 커야합니다.")
    private Long amount;

    @Builder
    private PointChargeRequest(Long memberId, Long amount) {
        this.memberId = memberId;
        this.amount = amount;
    }

    public PointChargeServiceRequest toServiceRequest() {
        return PointChargeServiceRequest.builder()
                .memberId(memberId)
                .amount(amount)
                .build();
    }
}
