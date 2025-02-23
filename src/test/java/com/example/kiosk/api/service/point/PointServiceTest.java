package com.example.kiosk.api.service.point;

import com.example.kiosk.domain.member.Member;
import com.example.kiosk.domain.member.MemberRepository;
import com.example.kiosk.domain.point.PointRepository;
import com.example.kiosk.exception.CommonErrorCode;
import com.example.kiosk.exception.RestApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.kiosk.exception.MemberErrorCode.INVALID_MEMBER;
import static com.example.kiosk.exception.PointErrorCode.INVALID_CHARGE_POINT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PointServiceTest {

    @InjectMocks
    private PointService pointService;

    @Mock
    private MemberRepository memberRepository;

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

    @DisplayName("충전하려는 포인트가 0이하이면 포인트를 충전에 실패한다.")
    @Test
    void chargeUnderZeroPoints() {
        // given
        Long memberId = 1L;
        Long amount = 0L;

        Member member = Member.builder()
                .name("정장꾸")
                .build();

        given(memberRepository.findById(memberId)).willReturn(Optional.of(member));

        // when
        assertThatThrownBy(() -> pointService.chargePoints(memberId, amount))
                .isInstanceOf(RestApiException.class)
                .hasMessage(INVALID_CHARGE_POINT.getMessage());

        // then
        then(memberRepository).should(times(1)).findById(memberId);
        then(memberRepository).should(never()).save(any(Member.class));
    }

    @DisplayName("존재하지 않는 사용자의 포인트를 충전하려는 경우 포인트를 충전에 실패한다.")
    @Test
    void chargePointsWithInvalidMember() {
        // given
        Long memberId = -1L;
        Long amount = 10000L;

        given(memberRepository.findById(memberId)).willReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> pointService.chargePoints(memberId, amount))
                .isInstanceOf(RestApiException.class)
                .hasMessage(INVALID_MEMBER.getMessage());

        // then
        then(memberRepository).should(times(1)).findById(memberId);
        then(memberRepository).should(never()).save(any(Member.class));
    }
}
