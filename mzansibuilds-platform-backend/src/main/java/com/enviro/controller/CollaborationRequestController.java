package com.enviro.controller;

import com.enviro.domain.CollaborationRequest;
import com.enviro.service.CollaborationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collaboration")
@CrossOrigin(origins = "http://localhost:5175")
public class CollaborationRequestController {

    private final CollaborationRequestService service;

    @Autowired
    public CollaborationRequestController(CollaborationRequestService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<CollaborationRequest> create(@RequestBody CollaborationRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<CollaborationRequest> read(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.read(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CollaborationRequest>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<CollaborationRequest> update(@RequestBody CollaborationRequest request) {
        return ResponseEntity.ok(service.update(request));
    }
}