package edu.vwcc.guestbook.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.vwcc.guestbook.model.Comment;
import edu.vwcc.guestbook.model.CommentRepository;
import edu.vwcc.guestbook.model.Guest;
import edu.vwcc.guestbook.model.GuestRepository;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/guests")
    public Guest createGuest(@RequestBody Guest guest) {
        Comment welcomeComment = new Comment("Welcome to your Guest page!", "SYSTEM");
        guest.addComment(welcomeComment);
        return guestRepository.save(guest);
    }

    @PostMapping("/guests/{id}/comments")
    public Comment addComment(@PathVariable Long id, @RequestBody Comment comment) {
        Guest guest = guestRepository.findById(id).orElseThrow();
        comment.setGuest(guest);
        return commentRepository.save(comment);
    }

    @DeleteMapping("/guests/{guestId}/comments/{commentId}")
    public void deleteComment(@PathVariable Long guestId, @PathVariable Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if (comment.getGuest().getId().equals(guestId)) {
            commentRepository.delete(comment);
        }
    }
}

