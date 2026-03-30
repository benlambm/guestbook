package edu.vwcc.guestbook.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.vwcc.guestbook.model.Guest;
import edu.vwcc.guestbook.model.GuestRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private GuestRepository guestRepository;

    @PostMapping("/guests")
    public ResponseEntity<Guest> createGuest(@Valid @RequestBody Guest guest) {
        Guest saved = guestRepository.save(guest);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/guests/{deleteToken}")
    public ResponseEntity<Void> deleteGuest(@PathVariable String deleteToken) {
        Guest guest = guestRepository.findByDeleteToken(deleteToken)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No guestbook entry found for that token"));
        guestRepository.delete(guest);
        return ResponseEntity.noContent().build();
    }
}
