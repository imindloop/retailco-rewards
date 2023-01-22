package com.example.retailcorewards.boostrapdata;

import com.example.retailcorewards.repositories.CustomerRepository;
import com.example.retailcorewards.repositories.OrderRepository;
import com.example.retailcorewards.web.model.CustomerDto;
import com.example.retailcorewards.web.model.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Component
public class ObjectLoader implements CommandLineRunner {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final OrderRepository orderRepository;

    public ObjectLoader(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public CustomerDto ramza = CustomerDto.builder()
            .id("Ramlve")
            .firstName("Ramza")
            .lastName("Beoulve")
            .email("ramza.beoulve@fftexample.com")
            .build();

    public void run(String... args) throws Exception {
        loadCustomers();
        loadOrders();
    }

    private void loadCustomers() {
        if (customerRepository.count() == 0) {

            // Creating Ramza

            customerRepository.save(CustomerDto.builder()
                    .id("Ramlve")
                    .firstName("Ramza")
                    .lastName("Beoulve")
                    .email("ramza.beoulve@fftexample.com")
                    .build());

            // Creating Delita

            customerRepository.save(CustomerDto.builder()
                    .id("Delral")
                    .firstName("Delita")
                    .lastName("Heiral")
                    .email("delita.heiral@fftexample.com")
                    .build());

            customerRepository.save(CustomerDto.builder()
                    .id("Musnsa")
                    .firstName("Mustadio")
                    .lastName("Bunansa")
                    .email("mustadio.bunansa@fftexample.com")
                    .build());

            customerRepository.save(CustomerDto.builder()
                    .id("agraks")
                    .firstName("Agrias")
                    .lastName("Oaks")
                    .email("agrias.oaks@fftexample.com\n")
                    .build());

            customerRepository.save(CustomerDto.builder()
                    .id("Rapena")
                    .firstName("Rapha")
                    .lastName("Galthena")
                    .email("rapha.galthena@fftexample.com\n")
                    .build());

            customerRepository.save(CustomerDto.builder()
                    .id("Marena")
                    .firstName("Marach")
                    .lastName("Galthena")
                    .email("marach.galthena@fftexample.com")
                    .build());

            customerRepository.save(CustomerDto.builder()
                    .id("Ciddeu")
                    .firstName("Cidolfus")
                    .lastName("Orlandeu")
                    .email("cidolfus.orlandeu@fftexample.com")
                    .build());

            customerRepository.save(CustomerDto.builder()
                    .id("Mellle")
                    .firstName("Meliadoul")
                    .lastName("Tengille")
                    .email("meliadoul.tengille@fftexample.com")
                    .build());

            customerRepository.save(CustomerDto.builder()
                    .id("Beomus")
                    .firstName("Beowulf")
                    .lastName("Cadmus")
                    .email("beowulf.cadmus@fftexample.com")
                    .build());

            customerRepository.save(CustomerDto.builder()
                    .id("Reilar")
                    .firstName("Reis")
                    .lastName("Duelar")
                    .email("reis.duelar@fftexample.com")
                    .build());

            customerRepository.save(CustomerDto.builder()
                    .id("Almlve")
                    .firstName("Alma")
                    .lastName("Beoulve")
                    .email("alma.beoulve@fftexample.com")
                    .build());

            customerRepository.save(CustomerDto.builder()
                    .id("Dyclve")
                    .firstName("Dycedarg")
                    .lastName("Beoulve")
                    .email("dycedarg.beoulve@fftexample.com")
                    .build());


            System.out.println("Data boostrapping completed. " + customerRepository.count() + " customers were loaded.");

        }
    }

    private void loadOrders() {

        if (orderRepository.count() == 0) {

            orderRepository.save(OrderDto.builder()
                    .id("order1")
                    .description("Potion x 5, Ether x 1, Antidote x 1, Black Mail Armor x 1")
                    .creationDate(OffsetDateTime.now())
                    .total(new BigDecimal("120"))
                    .customer(ramza)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order1")
                    .description("Sabre x 5, X Ether x 1, Key x 1, Cloud Sword x 1")
                    .creationDate(OffsetDateTime.now())
                    .total(new BigDecimal("80"))
                    .customer(ramza)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order2")
                    .description("Light Sword x 1, X-Potion x 1, Antidote x 1, Echo Herb x 1")
                    .creationDate(OffsetDateTime.now())
                    .total(new BigDecimal("75"))
                    .customer(ramza)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order3")
                    .description("Crystal Shield x 2, Soft x 1, holy  Grail x 1, Elixir x 1")
                    .creationDate(OffsetDateTime.now())
                    .total(new BigDecimal("200"))
                    .customer(ramza)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order4")
                    .description("Speed Boots x 1, Mithril Vest x 1, Lamp x 1, Atma Weapon x 1")
                    .creationDate(OffsetDateTime.now())
                    .total(new BigDecimal("35"))
                    .customer(ramza)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order5")
                    .description("Ifrit Stone x 5, Shuriken x 40, Shiva Stone x 1, Bahamut Stone x 1")
                    .creationDate(OffsetDateTime.now())
                    .total(new BigDecimal("400"))
                    .customer(ramza)
                    .build());

            orderRepository.save(OrderDto.builder()
                    .id("order6")
                    .description("Judas Kiss x 1, Landmine tracker x 1, Morning Star x 1, Sunray Knife x 1")
                    .creationDate(OffsetDateTime.now())
                    .total(new BigDecimal("60"))
                    .customer(ramza)
                    .build());

            System.out.println("Data boostrapping completed. " + orderRepository.count() + " orders were loaded.");

        }
    }


}
