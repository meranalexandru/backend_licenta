package com.meran.backendlicenta.repositories;

import com.meran.backendlicenta.models.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    public Comment deleteCommentByCommentId(Long id);

    void deleteByCommentId(Long commentId);

    Optional<Comment> findByCommentId(Long commentId);
}
