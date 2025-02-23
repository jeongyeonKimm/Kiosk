package com.example.kiosk.domain.point;

import com.example.kiosk.domain.member.Member;
import com.example.kiosk.domain.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class PointRepositoryTest {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("각 회원은 포인트를 가진다.")
    @Test
    void savePoint() {
        // given
        Member member = Member.builder()
                .name("정장꾸")
                .build();
        memberRepository.save(member);

        Point point = Point.builder()
                .member(member)
                .amount(10000L)
                .build();

        // when
        Point savedPoint = pointRepository.save(point);

        // then
        assertThat(savedPoint.getId()).isNotNull();
        assertThat(savedPoint.getMember().getName()).isEqualTo("정장꾸");
        assertThat(savedPoint.getAmount()).isEqualTo(10000);
    }
}
