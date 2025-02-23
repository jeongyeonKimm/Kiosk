package com.example.kiosk.domain.point;

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

    @DisplayName("")
    @Test
    void savePoint() {
        // given
        Member member = Member.builder()
                .name("정장꾸")
                .build();

        Point point = Point.builder()
                .member(member)
                .amount(10000)
                .build();

        // when
        Point savedPoint = pointRepository.save(point);

        // then
        assertThat(savedPoint.getId()).isNotNull();
        assertThat(savedPoint.getMember().getName()).isEqualTo("정장꾸");
        assertThat(savedPoint.getAmount()).isEqualTo(10000);
    }
}
