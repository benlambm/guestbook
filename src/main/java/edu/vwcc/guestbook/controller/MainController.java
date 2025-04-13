package edu.vwcc.guestbook.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import edu.vwcc.guestbook.model.Guest;
import edu.vwcc.guestbook.model.GuestRepository;

@Controller
public class MainController {
    @Autowired
    private GuestRepository guestRepository;

    @GetMapping("/")
    public String guestList(Model model) {
        model.addAttribute("guests", guestRepository.findAllByOrderByCreatedAtAsc());
        return "index";
    }

    @GetMapping("/guests/{id}")
    public String guestDetails(@PathVariable Long id, Model model) {
        Optional<Guest> guestOptional = guestRepository.findById(id);   
        if (guestOptional.isEmpty()) {
            // Return 404 status and let Spring Boot handle showing the error page
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guest not found with ID: " + id);
        }       
        model.addAttribute("guest", guestOptional.get());
        return "guest";
    }
}
