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

    @DisplayName("원하는 금액만큼 포인트를 충전한다.")
    @Test
    void chargePoint() {
        // given
        Long memberId = 1L;
        Long amount = 10000L;

        Member member = Member.builder()
                .name("정장꾸")
                .build();

        Point point = Point.builder()
                .member(member)
                .amount(10000L)
                .build();

        given(memberRepository.findById(memberId)).willReturn(Optional.of(member));
        given(pointRepository.findByMemberId(memberId)).willReturn(Optional.of(point));

        // when
        pointService.chargePoint(memberId, amount);

        // then
        assertThat(point.getAmount()).isEqualTo(20000L);

        verify(pointRepository, times(1)).save(point);
    }
}
