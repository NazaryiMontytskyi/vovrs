package com.dnvr.vovrs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubCreationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private StructuralUnit structuralUnit;

    @Enumerated(EnumType.STRING)
    private ClubDirection clubDirection;

    @OneToOne
    @JoinColumn(name = "related_entity_id", nullable = false)
    private RelatedDocument regulation;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToMany
    @JoinTable(
            name = "club_request_coleaders",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> coLeader = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();
}
