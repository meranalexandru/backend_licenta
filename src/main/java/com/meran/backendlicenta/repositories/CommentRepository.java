package com.meran.backendlicenta.repositories;

import com.meran.backendlicenta.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    public Comment deleteCommentById(Long id);
}
