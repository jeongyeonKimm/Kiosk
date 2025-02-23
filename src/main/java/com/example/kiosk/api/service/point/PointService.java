package com.example.kiosk.api.service.point;

import com.example.kiosk.api.service.point.request.PointChargeServiceRequest;
import com.example.kiosk.api.service.point.response.PointResponse;
import com.example.kiosk.domain.member.Member;
import com.example.kiosk.domain.member.MemberRepository;
import com.example.kiosk.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.kiosk.exception.MemberErrorCode.INVALID_MEMBER;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PointService {

    private final MemberRepository memberRepository;

    @Transactional
    public PointResponse chargePoints(PointChargeServiceRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RestApiException(INVALID_MEMBER));

        member.chargePoints(request.getAmount());
        memberRepository.save(member);

        return PointResponse.of(member);
    }
}
