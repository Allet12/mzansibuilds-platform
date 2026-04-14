package com.enviro.controller;

import com.enviro.domain.Project;
import com.enviro.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "http://localhost:5175")
public class ProjectController {

    private final ProjectService service;

    @Autowired
    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Project> create(@RequestBody Project project) {
        return new ResponseEntity<>(service.create(project), HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Project> read(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.read(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Project> update(@RequestBody Project project) {
        return ResponseEntity.ok(service.update(project));
    }
}