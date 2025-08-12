package com.dnvr.vovrs.repository;

import com.dnvr.vovrs.model.Club;
import com.dnvr.vovrs.model.ClubDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findById(long id);

    @Query("SELECT c FROM Club c WHERE c.direction = :direction")
    List<Club> findByDirection(ClubDirection direction);

    @Query("SELECT DISTINCT c FROM Club c JOIN c.leaders l WHERE l.id = :leaderId")
    List<Club> findByLeader(long leaderId);
}
