package com.dnvr.vovrs.dto;

import com.dnvr.vovrs.model.ClubDirection;

import java.util.Set;

public record ClubCreationRequestBody(String name, ClubDirection direction, Set<Long> coLeadersIds) {
}
