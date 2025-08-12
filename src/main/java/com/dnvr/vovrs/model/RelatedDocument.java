package com.dnvr.vovrs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RelatedDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
