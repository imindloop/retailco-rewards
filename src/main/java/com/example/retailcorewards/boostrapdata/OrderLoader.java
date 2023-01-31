package com.example.retailcorewards.boostrapdata;

import com.example.retailcorewards.repositories.OrderRepository;
import com.example.retailcorewards.web.model.CustomerDto;
import com.example.retailcorewards.web.model.OrderDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class OrderLoader implements CommandLineRunner {

    private CustomerDto ramza = CustomerDto.builder()
            .id("Ramlve")
            .firstName("Ramza")
            .lastName("Beoulve")
            .email("ramza.beoulve@fftexample.com")
            .build();

    private CustomerDto delita = CustomerDto.builder()
            .id("Delral")
            .firstName("Delita")
            .lastName("Heiral")
            .email("delita.heiral@fftexample.com")
            .build();

    private CustomerDto mustadio = CustomerDto.builder()
            .id("Musnsa")
            .firstName("Mustadio")
            .lastName("Bunansa")
            .email("mustadio.bunansa@fftexample.com")
            .build();

    private CustomerDto agrias = CustomerDto.builder()
            .id("agraks")
            .firstName("Agrias")
            .lastName("Oaks")
            .email("agrias.oaks@fftexample.com\n")
            .build();

    private OrderRepository orderRepository;

    public OrderLoader(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadOrders();
    }

    private void loadOrders() {

        LocalDate localDate1 = LocalDate.parse("2022-12-03");
        LocalDate localDate2 = LocalDate.parse("2022-11-03");
        LocalDate localDate3 = LocalDate.parse("2022-10-03");

        if (orderRepository.count() == 0) {

            // Ramza orders

            orderRepository.save(OrderDto.builder()
                    .id("order1")
                    .description("Razor x 5, Brush x 1, Labrador x 1, Black Mail Armor x 1")
                    .creationDate(localDate1)
                    .total(new BigDecimal("70"))
                    .customer(ramza)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order2")
                    .description("Sabre x 5, X Ether x 1, Key x 1, Cloud Sword x 1")
                    .creationDate(localDate2)
                    .total(new BigDecimal("80"))
                    .customer(ramza)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order3")
                    .description("Light Sword x 1, X-Potion x 1, Antidote x 1, Echo Herb x 1")
                    .creationDate(localDate3)
                    .total(new BigDecimal("55"))
                    .customer(ramza)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order4")
                    .description("Crystal Shield x 2, Soft x 1, holy  Grail x 1, Elixir x 1")
                    .creationDate(localDate1)
                    .total(new BigDecimal("50"))
                    .customer(ramza)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order5")
                    .description("Speed Boots x 1, Mithril Vest x 1, Lamp x 1, Atma Weapon x 1")
                    .creationDate(localDate2)
                    .total(new BigDecimal("100"))
                    .customer(ramza)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order6")
                    .description("Ifrit Stone x 5, Shuriken x 40, Shiva Stone x 1, Bahamut Stone x 1")
                    .creationDate(localDate3)
                    .total(new BigDecimal("200"))
                    .customer(ramza)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order7")
                    .description("Judas Kiss x 1, Landmine tracker x 1, Morning Star x 1, Sunray Knife x 1")
                    .creationDate(localDate1)
                    .total(new BigDecimal("65"))
                    .customer(ramza)
                    .build());


            // Delita orders

            orderRepository.save(OrderDto.builder()
                    .id("order8")
                    .description("Potion x 5, Ether x 1, Antidote x 1, Black Mail Armor x 1")
                    .creationDate(localDate1)
                    .total(new BigDecimal("95"))
                    .customer(delita)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order9")
                    .description("Sabre x 5, X Ether x 1, Key x 1, Cloud Sword x 1")
                    .creationDate(localDate2)
                    .total(new BigDecimal("1500"))
                    .customer(delita)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order10")
                    .description("Light Sword x 1, X-Potion x 1, Antidote x 1, Echo Herb x 1")
                    .creationDate(localDate3)
                    .total(new BigDecimal("87"))
                    .customer(delita)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order11")
                    .description("Crystal Shield x 2, Soft x 1, holy  Grail x 1, Elixir x 1")
                    .creationDate(localDate1)
                    .total(new BigDecimal("92"))
                    .customer(delita)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order12")
                    .description("Speed Boots x 1, Mithril Vest x 1, Lamp x 1, Atma Weapon x 1")
                    .creationDate(localDate2)
                    .total(new BigDecimal("101"))
                    .customer(delita)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order13")
                    .description("Ifrit Stone x 5, Shuriken x 40, Shiva Stone x 1, Bahamut Stone x 1")
                    .creationDate(localDate3)
                    .total(new BigDecimal("320"))
                    .customer(delita)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order14")
                    .description("Judas Kiss x 1, Landmine tracker x 1, Morning Star x 1, Sunray Knife x 1")
                    .creationDate(localDate1)
                    .total(new BigDecimal("170"))
                    .customer(delita)
                    .build());


            // Mustadio orders

            orderRepository.save(OrderDto.builder()
                    .id("order15")
                    .description("Potion x 5, Ether x 1, Antidote x 1, Black Mail Armor x 1")
                    .creationDate(localDate1)
                    .total(new BigDecimal("560"))
                    .customer(mustadio)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order16")
                    .description("Sabre x 5, X Ether x 1, Key x 1, Cloud Sword x 1")
                    .creationDate(localDate2)
                    .total(new BigDecimal("63"))
                    .customer(mustadio)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order17")
                    .description("Light Sword x 1, X-Potion x 1, Antidote x 1, Echo Herb x 1")
                    .creationDate(localDate3)
                    .total(new BigDecimal("58"))
                    .customer(mustadio)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order18")
                    .description("Crystal Shield x 2, Soft x 1, holy  Grail x 1, Elixir x 1")
                    .creationDate(localDate1)
                    .total(new BigDecimal("570"))
                    .customer(mustadio)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order19")
                    .description("Speed Boots x 1, Mithril Vest x 1, Lamp x 1, Atma Weapon x 1")
                    .creationDate(localDate2)
                    .total(new BigDecimal("56"))
                    .customer(mustadio)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order20")
                    .description("Ifrit Stone x 5, Shuriken x 40, Shiva Stone x 1, Bahamut Stone x 1")
                    .creationDate(localDate3)
                    .total(new BigDecimal("450"))
                    .customer(mustadio)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order21")
                    .description("Judas Kiss x 1, Landmine tracker x 1, Morning Star x 1, Sunray Knife x 1")
                    .creationDate(localDate1)
                    .total(new BigDecimal("88"))
                    .customer(mustadio)
                    .build());


            // Agrias orders

            orderRepository.save(OrderDto.builder()
                    .id("order22")
                    .description("Potion x 5, Ether x 1, Antidote x 1, Black Mail Armor x 1")
                    .creationDate(localDate1)
                    .total(new BigDecimal("150"))
                    .customer(agrias)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order23")
                    .description("Sabre x 5, X Ether x 1, Key x 1, Cloud Sword x 1")
                    .creationDate(localDate2)
                    .total(new BigDecimal("69"))
                    .customer(agrias)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order24")
                    .description("Light Sword x 1, X-Potion x 1, Antidote x 1, Echo Herb x 1")
                    .creationDate(localDate3)
                    .total(new BigDecimal("81"))
                    .customer(agrias)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order25")
                    .description("Crystal Shield x 2, Soft x 1, holy  Grail x 1, Elixir x 1")
                    .creationDate(localDate1)
                    .total(new BigDecimal("67"))
                    .customer(agrias)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order26")
                    .description("Speed Boots x 1, Mithril Vest x 1, Lamp x 1, Atma Weapon x 1")
                    .creationDate(localDate2)
                    .total(new BigDecimal("99"))
                    .customer(agrias)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order27")
                    .description("Ifrit Stone x 5, Shuriken x 40, Shiva Stone x 1, Bahamut Stone x 1")
                    .creationDate(localDate3)
                    .total(new BigDecimal("360"))
                    .customer(agrias)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order28")
                    .description("Judas Kiss x 1, Landmine tracker x 1, Morning Star x 1, Sunray Knife x 1")
                    .creationDate(localDate1)
                    .total(new BigDecimal("58"))
                    .customer(agrias)
                    .build());

            System.out.println("Data boostrapping completed. " + orderRepository.count() + " orders were loaded.");

        }
    }

}
