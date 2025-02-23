package com.example.kiosk.api.controller.point;

import com.example.kiosk.api.controller.point.request.PointChargeRequest;
import com.example.kiosk.api.service.point.PointService;
import com.example.kiosk.api.service.point.response.PointResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PointController {

    private final PointService pointService;

    @PostMapping("/api/points/charge")
    public ResponseEntity<PointResponse> chargePoints(@RequestBody @Valid PointChargeRequest request) {
        PointResponse response = pointService.chargePoints(request.toServiceRequest());

        return ResponseEntity.ok(response);
    }
}
