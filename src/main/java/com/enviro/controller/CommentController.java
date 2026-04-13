package com.enviro.controller;

import com.enviro.domain.Comment;
import com.enviro.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

    private final CommentService service;

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Comment> create(@RequestBody Comment comment) {
        return new ResponseEntity<>(service.create(comment), HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Comment> read(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.read(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Comment> update(@RequestBody Comment comment) {
        return ResponseEntity.ok(service.update(comment));
    }
}