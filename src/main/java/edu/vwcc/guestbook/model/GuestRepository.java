package edu.vwcc.guestbook.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    List<Guest> findAllByOrderByCreatedAtAsc();

    Optional<Guest> findByDeleteToken(String deleteToken);
}



