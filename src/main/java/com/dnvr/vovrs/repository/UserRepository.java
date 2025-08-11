package com.dnvr.vovrs.repository;

import com.dnvr.vovrs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByNameAndSurname(String name, String surname);

    Optional<User> findById(Long id);

}
