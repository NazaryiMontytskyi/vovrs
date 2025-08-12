package com.dnvr.vovrs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate orderDate;
    private String numberOfOrder;
    private String description;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club relatedClub;

    @OneToOne
    @Column(nullable = true)
    private RelatedDocument relatedDocument;
}
