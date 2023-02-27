package com.meran.backendlicenta.repositories;

import com.meran.backendlicenta.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    public Comment deleteCommentByCommentId(Long id);

    public Comment findCommentByCommentId(Long id);
}
