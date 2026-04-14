package com.enviro.controller;

import com.enviro.domain.Post;
import com.enviro.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "http://localhost:5175")
public class PostController {

    private final PostService service;

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        return new ResponseEntity<>(service.create(post), HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Post> read(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.read(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Post> update(@RequestBody Post post) {
        return ResponseEntity.ok(service.update(post));
    }
}