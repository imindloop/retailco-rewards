package com.example.retailcorewards.boostrapdata;

import com.example.retailcorewards.repositories.CustomerRepository;
import com.example.retailcorewards.repositories.OrderRepository;
import com.example.retailcorewards.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomerLoader implements CommandLineRunner {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final OrderRepository orderRepository;

    public CustomerLoader(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public void run(String... args) throws Exception {
        loadCustomers();
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

}
