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

        CustomerDto ramza = CustomerDto.builder()
            .id("Ramlve")
            .firstName("Ramza")
            .lastName("Beoulve")
            .email("ramza.beoulve@fftexample.com")
            .build();

        CustomerDto delita = CustomerDto.builder()
            .id("Delral")
            .firstName("Delita")
            .lastName("Heiral")
            .email("delita.heiral@fftexample.com")
            .build();

        LocalDate localDate1 = LocalDate.parse("2022-12-03");
        LocalDate localDate2 = LocalDate.parse("2022-11-03");
        LocalDate localDate3 = LocalDate.parse("2022-10-03");

        return new Object[] {
            new Object[]{
                new ArrayList<OrderDto>() {{

                    // Ramza Orders

                    add(OrderDto.builder()
                        .id("order1")
                        .description("Razor x 5, Brush x 1, Labrador x 1, Black Mail Armor x 1")
                        .creationDate(LocalDate.parse("2022-12-03"))
                        .total(new BigDecimal("70"))
                        .customer(ramza)
                        .build()
                    );
                    add(OrderDto.builder()
                        .id("order2")
                        .description("Sabre x 5, X Ether x 1, Key x 1, Cloud Sword x 1")
                        .creationDate(localDate2)
                        .total(new BigDecimal("80"))
                        .customer(ramza)
                        .build()
                    );

                    add(OrderDto.builder()
                        .id("order3")
                        .description("Light Sword x 1, X-Potion x 1, Antidote x 1, Echo Herb x 1")
                        .creationDate(localDate3)
                        .total(new BigDecimal("55"))
                        .customer(ramza)
                        .build()
                    );

                    add(OrderDto.builder()
                        .id("order4")
                        .description("Crystal Shield x 2, Soft x 1, holy  Grail x 1, Elixir x 1")
                        .creationDate(localDate1)
                        .total(new BigDecimal("50"))
                        .customer(ramza)
                        .build()
                    );

                    add(OrderDto.builder()
                        .id("order5")
                        .description("Speed Boots x 1, Mithril Vest x 1, Lamp x 1, Atma Weapon x 1")
                        .creationDate(localDate2)
                        .total(new BigDecimal("100"))
                        .customer(ramza)
                        .build()
                    );

                    add(OrderDto.builder()
                        .id("order6")
                        .description("Ifrit Stone x 5, Shuriken x 40, Shiva Stone x 1, Bahamut Stone x 1")
                        .creationDate(localDate3)
                        .total(new BigDecimal("200"))
                        .customer(ramza)
                        .build()
                    );

                    add(OrderDto.builder()
                        .id("order7")
                        .description("Judas Kiss x 1, Landmine tracker x 1, Morning Star x 1, Sunray Knife x 1")
                        .creationDate(localDate1)
                        .total(new BigDecimal("65"))
                        .customer(ramza)
                        .build()
                    );

                    // Delita Orders

                    add(OrderDto.builder()
                        .id("order8")
                        .description("Potion x 5, Ether x 1, Antidote x 1, Black Mail Armor x 1")
                        .creationDate(localDate1)
                        .total(new BigDecimal("200"))
                        .customer(delita)
                        .build()
                    );

                    add(OrderDto.builder()
                        .id("order9")
                        .description("Sabre x 5, X Ether x 1, Key x 1, Cloud Sword x 1")
                        .creationDate(localDate2)
                        .total(new BigDecimal("1500"))
                        .customer(delita)
                        .build()
                    );

                    add(OrderDto.builder()
                        .id("order10")
                        .description("Light Sword x 1, X-Potion x 1, Antidote x 1, Echo Herb x 1")
                        .creationDate(localDate3)
                        .total(new BigDecimal("87"))
                        .customer(delita)
                        .build()
                    );

                    add(OrderDto.builder()
                        .id("order11")
                        .description("Crystal Shield x 2, Soft x 1, holy  Grail x 1, Elixir x 1")
                        .creationDate(localDate1)
                        .total(new BigDecimal("92"))
                        .customer(delita)
                        .build()
                    );

                    add(OrderDto.builder()
                            .id("order13")
                            .description("Ifrit Stone x 5, Shuriken x 40, Shiva Stone x 1, Bahamut Stone x 1")
                            .creationDate(localDate3)
                            .total(new BigDecimal("320"))
                            .customer(delita)
                            .build()
                    );
                }},

                new HashMap<String, Map<String, Integer>>() {{
                    put("Delita Heiral", new HashMap<String, Integer>(){{
                        put("DECEMBER", 142);
                        put("NOVEMBER", 1400);
                        put("OCTOBER", 257);
                    }});
                    put("Ramza Beoulve", new HashMap<String, Integer>(){{
                        put("DECEMBER", 35);
                        put("NOVEMBER", 30);
                        put("OCTOBER", 105);
                    }});
                }}
            }
        };
    }

}