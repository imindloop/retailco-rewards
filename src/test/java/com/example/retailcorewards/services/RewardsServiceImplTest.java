package com.example.retailcorewards.services;

import com.example.retailcorewards.web.model.CustomerDto;
import com.example.retailcorewards.web.model.OrderDto;
import junitparams.JUnitParamsRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(JUnitParamsRunner.class)
class RewardsServiceImplTest {

    private RewardsServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new RewardsServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("testLowerThresholdNumberValues")
    public void testRewardPointsCalculationForPurchaseInLowerThreshold(int orderTotal, int lowerRewardsThreshold, int rewardPointsForLowerThreshold, int result) {
        assertThat(underTest.calculatePointsInLowerThreshold(orderTotal, lowerRewardsThreshold, rewardPointsForLowerThreshold)).isEqualTo(result);
    }

    private static Object[] testLowerThresholdNumberValues() {
        return new Object[] {
                new Object[] { 45, 50, 1, 0 },
                new Object[] { 50, 50, 1, 0 },
                new Object[] { 55, 50, 1, 5 },
                new Object[] { 60, 50, 1, 10 },
                new Object[] { 65, 50, 1, 15 },
                new Object[] { 70, 50, 1, 20 },
                new Object[] { 75, 50, 1, 25 },
                new Object[] { 80, 50, 1, 30 },
                new Object[] { 85, 50, 1, 35 },
                new Object[] { 90, 50, 1, 40 },
                new Object[] { 95, 50, 1, 45 },
                new Object[] { 99, 50, 1, 49 },
                new Object[] { 100, 50, 1, 50 }
        };
    }

    @ParameterizedTest
    @MethodSource("testUpperThresholdNumberValues")
    public void testRewardPointsCalculationForPurchaseInUpperThreshold(int orderTotal, int lowerRewardsThreshold, int upperRewardsThreshold, int rewardPointsForUpperThreshold, int result) {
        assertThat(underTest.calculatePointsInUpperThreshold(orderTotal, lowerRewardsThreshold, upperRewardsThreshold, rewardPointsForUpperThreshold)).isEqualTo(result);
    }

    private static Object[] testUpperThresholdNumberValues() {
        return new Object[] {
                new Object[] { 100, 50, 100, 2, 50 },
                new Object[] { 110, 50, 100, 2, 70 },
                new Object[] { 150, 50, 100, 2, 150 },
                new Object[] { 220, 50, 100, 2, 290 },
                new Object[] { 300, 50, 100, 2, 450 },
                new Object[] { 500, 50, 100, 2, 850 },
                new Object[] { 600, 50, 100, 2, 1050 },
                new Object[] { 800, 50, 100, 2, 1450 },
                new Object[] { 1100, 50, 100, 2, 2050 },
                new Object[] { 5000, 50, 100, 2, 9850 },
                new Object[] { 7500, 50, 100, 2, 14850 },
                new Object[] { 11111, 50, 100, 2, 22072 },
        };
    }

    @ParameterizedTest
    @MethodSource("testValues")
    public void testGetRewardPoints(List<OrderDto> orders, Map<String, Map<String, Integer>> result) throws Exception {
        assertThat(underTest.getRewardPoints(orders)).isEqualTo(result);
    }

    private static Object[] testValues() {
        return new Object[] {
            new Object[]{
                new ArrayList<OrderDto>() {
                    {
                        add(OrderDto.builder()
                                .id("order1")
                                .description("Razor x 5, Brush x 1, Labrador x 1, Black Mail Armor x 1")
                                .creationDate(LocalDate.parse("2022-12-03"))
                                .total(new BigDecimal("70"))
                                .customer(CustomerDto.builder()
                                        .id("Ramlve")
                                        .firstName("Ramza")
                                        .lastName("Beoulve")
                                        .email("ramza.beoulve@fftexample.com")
                                        .build())
                                .build()
                        );
                    }
                },
                new HashMap<String, Map<String, Integer>>() {{
                    put("Ramza Beoulve", new HashMap<String, Integer>(){{
                        put("DECEMBER", 20);
                    }});
                }}
            }
        };
    }

}