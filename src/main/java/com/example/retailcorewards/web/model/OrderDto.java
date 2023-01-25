package com.example.retailcorewards.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class OrderDto {

    @Id
    //@Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private String id;
    private BigDecimal total;
    String description;

    //@Column(updatable = false)
    LocalDate creationDate;

    @ManyToOne
    CustomerDto customer;

}
