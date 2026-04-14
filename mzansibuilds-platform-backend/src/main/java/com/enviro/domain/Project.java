package com.enviro.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private String stage; // e.g., Planning, Development, Testing, Completed

    private String supportRequired;

    private boolean completed;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CollaborationRequest> collaborationRequests;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Milestone> milestones;

    protected Project() {}

    public Project(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.stage = builder.stage;
        this.supportRequired = builder.supportRequired;
        this.completed = builder.completed;
        this.createdAt = builder.createdAt;
        this.user = builder.user;
        this.posts = builder.posts;
        this.collaborationRequests = builder.collaborationRequests;
        this.milestones = builder.milestones;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getStage() { return stage; }
    public String getSupportRequired() { return supportRequired; }
    public boolean isCompleted() { return completed; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public User getUser() { return user; }
    public List<Post> getPosts() { return posts; }
    public List<CollaborationRequest> getCollaborationRequests() { return collaborationRequests; }
    public List<Milestone> getMilestones() { return milestones; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setStage(String stage) { this.stage = stage; }
    public void setSupportRequired(String supportRequired) { this.supportRequired = supportRequired; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public void setUser(User user) { this.user = user; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Project{id=" + id + ", name='" + name + "'}";
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private String stage;
        private String supportRequired;
        private boolean completed;
        private LocalDateTime createdAt;
        private User user;
        private List<Post> posts;
        private List<CollaborationRequest> collaborationRequests;
        private List<Milestone> milestones;

        public Builder setId(Long id) { this.id = id; return this; }
        public Builder setName(String name) { this.name = name; return this; }
        public Builder setDescription(String description) { this.description = description; return this; }
        public Builder setStage(String stage) { this.stage = stage; return this; }
        public Builder setSupportRequired(String supportRequired) { this.supportRequired = supportRequired; return this; }
        public Builder setCompleted(boolean completed) { this.completed = completed; return this; }
        public Builder setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder setUser(User user) { this.user = user; return this; }
        public Builder setPosts(List<Post> posts) { this.posts = posts; return this; }
        public Builder setCollaborationRequests(List<CollaborationRequest> collaborationRequests) { this.collaborationRequests = collaborationRequests; return this; }
        public Builder setMilestones(List<Milestone> milestones) { this.milestones = milestones; return this; }

        public Builder copy(Project project) {
            this.id = project.id;
            this.name = project.name;
            this.description = project.description;
            this.stage = project.stage;
            this.supportRequired = project.supportRequired;
            this.completed = project.completed;
            this.createdAt = project.createdAt;
            this.user = project.user;
            this.posts = project.posts;
            this.collaborationRequests = project.collaborationRequests;
            this.milestones = project.milestones;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }
}