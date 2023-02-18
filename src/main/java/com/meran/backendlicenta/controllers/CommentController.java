package com.meran.backendlicenta.controllers;

import com.meran.backendlicenta.models.Comment;
import com.meran.backendlicenta.models.User;
import com.meran.backendlicenta.repositories.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostMapping("/create-comment")
    public ResponseEntity<Comment> signup(@RequestBody Comment comment) {

        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Comment> removeComment(@PathVariable Long commentId) {
        try {
            Comment deletedComment = commentRepository.deleteCommentById(commentId);
            return ResponseEntity.ok(deletedComment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

        @PutMapping("/update-comment")
    public ResponseEntity<Comment> updateProject(@RequestBody Comment comment) {
        try {
            Comment updatedComment = commentRepository.findCommentById(comment.getId());
            updatedComment.setBody(comment.getBody());
            return ResponseEntity.ok(comment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
