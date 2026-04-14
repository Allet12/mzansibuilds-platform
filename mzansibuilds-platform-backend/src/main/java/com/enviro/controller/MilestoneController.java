package com.enviro.controller;

import com.enviro.domain.Milestone;
import com.enviro.service.MilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/milestone")
@CrossOrigin(origins = "http://localhost:5175")
public class MilestoneController {

    private final MilestoneService service;

    @Autowired
    public MilestoneController(MilestoneService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Milestone> create(@RequestBody Milestone milestone) {
        return new ResponseEntity<>(service.create(milestone), HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Milestone> read(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.read(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Milestone>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Milestone>> getByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(service.getByProjectId(projectId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Milestone> update(@RequestBody Milestone milestone) {
        return ResponseEntity.ok(service.update(milestone));
    }
}