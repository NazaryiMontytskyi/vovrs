package com.dnvr.vovrs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ClubDirection direction;

    @OneToMany(mappedBy = "relatedClub")
    private Set<Order> relatedOrders = new HashSet<>();

    @ManyToOne
    private StructuralUnit structuralUnit;

    @ManyToMany
    @JoinTable(
            name = "club_leaders",
            joinColumns = @JoinColumn(name = "club_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> leaders = new HashSet<>();

}
