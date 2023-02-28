package com.meran.backendlicenta.controllers;

import com.meran.backendlicenta.models.Comment;
import com.meran.backendlicenta.models.User;
import com.meran.backendlicenta.repositories.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostMapping("/create-comment")
    public ResponseEntity<Comment> signup(@RequestBody Comment comment) {
        comment.setCreatedAt(LocalDate.now());
        comment.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Comment> removeComment(@PathVariable Long commentId) {
        try {
            Comment deletedComment = commentRepository.deleteCommentByCommentId(commentId);
            return ResponseEntity.ok(deletedComment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update-comment")
    public ResponseEntity<Comment> updateComment(@RequestParam Long commentId, @RequestBody Comment updatedComment) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setBody(updatedComment.getBody());
            comment.setUpdatedAt(LocalDateTime.now());
            commentRepository.save(comment);
            return ResponseEntity.ok(comment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
