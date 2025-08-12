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
@Table(name = "club_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate orderDate;
    private String numberOfOrder;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", length = 50, columnDefinition = "varchar(50)")
    private OrderType orderType;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club relatedClub;

    @OneToOne
    @JoinColumn(name = "related_doc_id", nullable = true)
    private RelatedDocument relatedDocument;
}
