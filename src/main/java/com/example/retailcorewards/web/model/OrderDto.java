package com.example.retailcorewards.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class OrderDto {

    @Id
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private String id;
    private BigDecimal total;

    @CreationTimestamp
    @Column(updatable = false)
    Timestamp creationDate;

    @ManyToOne
    CustomerDto customer;

}
