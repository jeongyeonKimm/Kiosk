package com.example.kiosk.api.controller.point;

import com.example.kiosk.api.service.point.PointService;
import com.example.kiosk.exception.GlobalExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PointControllerTest {

    @InjectMocks
    private PointController pointController;

    @Mock
    private PointService pointService;

    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(pointController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @DisplayName("원하는 금액만큼 포인트를 충전한다.")
    @Test
    void chargePoints() throws Exception {
        // given
        PointChargeRequest request = PointChargeRequest.builder()
                .memberId(1L)
                .amount(10000L)
                .build();

        // when
        mockMvc.perform(
                        post("/api/points/charge")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value(1L))
                .andExpect(jsonPath("$.amount").value(10000L))
                .andDo(print());
    }

    @DisplayName("0 이하의 포인트 충전은 불가하다.")
    @Test
    void chargeUnderZeroPoints() throws Exception {
        // given
        PointChargeRequest request = PointChargeRequest.builder()
                .memberId(1L)
                .amount(0L)
                .build();

        // when
        mockMvc.perform(
                        post("/api/points/charge")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors[0].field").value("amount"))
                .andExpect(jsonPath("$.errors[0].message").value("충전할 금액은 0보다 커야합니다."))
                .andDo(print());
    }
}
