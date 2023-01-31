package com.example.retailcorewards.services;

import com.example.retailcorewards.repositories.OrderRepository;
import com.example.retailcorewards.web.model.CustomerDto;
import com.example.retailcorewards.web.model.OrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    private OrderServiceImpl underTest;

    CustomerDto delita = CustomerDto.builder()
            .id("Delral")
            .firstName("Delita")
            .lastName("Heiral")
            .email("delita.heiral@fftexample.com")
            .build();

    OrderDto order = OrderDto.builder()
            .id("order1")
            .description("Razor x 5, Brush x 1, Labrador x 1, Black Mail Armor x 1")
            .creationDate(LocalDate.of(2022, 12, 21))
            .total(new BigDecimal("70"))
            .customer(delita)
            .build();

    @BeforeEach
    void setUp() {
        underTest = new OrderServiceImpl(orderRepository);
    }

    @Test
    void checkItCanGetAllOrders() {
        // when
        underTest.getAllOrders();

        // then
        verify(orderRepository).findAll();
    }

    @Test
    void checkThatCanAddOrder() {
        // when
        underTest.addOrder(order);

        // then
        ArgumentCaptor<OrderDto> orderDtoArgumentCaptor = ArgumentCaptor.forClass(OrderDto.class);

        verify(orderRepository).save(orderDtoArgumentCaptor.capture());

        OrderDto capturedOrderDto = orderDtoArgumentCaptor.getValue();

        assertThat(capturedOrderDto).isEqualTo(order);
    }

    @Test
    void checkThatCanDeleteOrder() {
        // when
        underTest.deleteOrder(order);

        // then
        ArgumentCaptor<OrderDto> orderDtoArgumentCaptor = ArgumentCaptor.forClass(OrderDto.class);

        verify(orderRepository).delete(orderDtoArgumentCaptor.capture());

        OrderDto capturedOrder = orderDtoArgumentCaptor.getValue();

        assertThat(order).isEqualTo(capturedOrder);
    }

}