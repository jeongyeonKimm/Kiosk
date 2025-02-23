package com.example.kiosk.api.service.point;

import com.example.kiosk.domain.member.Member;
import com.example.kiosk.domain.member.MemberRepository;
import com.example.kiosk.domain.point.Point;
import com.example.kiosk.domain.point.PointRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PointServiceTest {

    @InjectMocks
    private PointService pointService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PointRepository pointRepository;

    @DisplayName("가지고 있던 포인트가 0인 사용자가 원하는 금액만큼 포인트를 충전한다.")
    @Test
    void chargePointsWithZeroPoint() {
        // given
        Long memberId = 1L;
        Long amount = 10000L;

        Member member = Member.builder()
                .name("정장꾸")
                .build();

        given(memberRepository.findById(memberId)).willReturn(Optional.of(member));

        // when
        pointService.chargePoints(memberId, amount);

        // then
        assertThat(member.getPoint().getAmount()).isEqualTo(10000L);

        verify(memberRepository, times(1)).save(member);
    }

    @DisplayName("가지고 있던 포인트가 0인 사용자가 원하는 금액만큼 포인트를 충전한다.")
    @Test
    void chargePointsWithPoints() {
        // given
        Long memberId = 1L;
        Long amount = 10000L;

        Member member = Member.builder()
                .name("정장꾸")
                .build();
        member.chargePoints(10000L);

        given(memberRepository.findById(memberId)).willReturn(Optional.of(member));

        // when
        pointService.chargePoints(memberId, amount);

        // then
        assertThat(member.getPoint().getAmount()).isEqualTo(20000L);

        verify(memberRepository, times(1)).save(member);
    }
}
