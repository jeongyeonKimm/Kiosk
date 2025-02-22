package com.example.kiosk.domain.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductSellingStatus {

    SELLING("판매 중"),
    SOLD_OUT("품절"),
    STOP_SELLING("판매 중지");

    private final String text;
}
