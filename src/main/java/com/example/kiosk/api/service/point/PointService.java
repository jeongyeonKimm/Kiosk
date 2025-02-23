package com.example.kiosk.api.service.point;

import com.example.kiosk.domain.member.Member;
import com.example.kiosk.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PointService {

    private final MemberRepository memberRepository;

    @Transactional
    public void chargePoints(Long memberId, Long amount) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(RuntimeException::new);

        member.chargePoints(amount);
        memberRepository.save(member);
    }
}
