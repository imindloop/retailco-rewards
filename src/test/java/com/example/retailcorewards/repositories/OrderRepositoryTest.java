package com.example.retailcorewards.repositories;

import com.example.retailcorewards.web.model.CustomerDto;
import com.example.retailcorewards.web.model.OrderDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository underTest;
    @Autowired
    private CustomerRepository customerRepository;

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
        underTest.deleteAll();
    }

    CustomerDto ramza = CustomerDto.builder()
            .id("ramlve")
            .firstName("Ramza")
            .lastName("Beoulve")
            .email("ramza.beoulve@fftexample.com")
            .build();

    OrderDto mockOrder = OrderDto.builder()
            .id("order1")
            .description("Razor x 5, Brush x 1, Labrador x 1, Black Mail Armor x 1")
            .creationDate(LocalDate.of(2022, 12, 24))
            .total(new BigDecimal("70"))
            .customer(ramza)
            .build();

    @Test
    void shouldCheckIfIsReturningOrders() {

        //given
        customerRepository.save(ramza);
        underTest.save(mockOrder);

        //when
        List<OrderDto> returnedList = underTest.findAll();

        //then
        assertThat(returnedList).isNotNull();
        assertFalse(returnedList.isEmpty());

        OrderDto returnedOrder = returnedList.get(0);
        CustomerDto returnedCustomer = returnedOrder.getCustomer();

        assertThat(returnedCustomer).isEqualTo(ramza);
        assertThat(returnedOrder).isEqualTo(mockOrder);
    }

    @Test
    void shouldCheckThatNoOrdersAreReturnedWhenNoOrdersArePresent() {

        //when
        List<OrderDto> returnedList = underTest.findAll();

        //then
        assertThat(returnedList).isNotNull();
        assertFalse(!returnedList.isEmpty());
    }

}