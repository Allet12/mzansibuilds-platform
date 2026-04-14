package com.enviro.service;

import com.enviro.domain.Comment;
import com.enviro.domain.Post;
import com.enviro.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements IServiceComment {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment read(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
    }

    @Override
    public Comment update(Comment comment) {
        Comment existing = read(comment.getId());

        if (comment.getText() != null)
            existing.setText(comment.getText());

        return commentRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public List<Comment> getByPost(Post post) {
        return commentRepository.findByPost(post);
    }
}