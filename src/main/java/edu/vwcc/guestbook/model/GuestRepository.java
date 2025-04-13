package edu.vwcc.guestbook.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
	
    List<Guest> findAllByOrderByCreatedAtAsc();
}



