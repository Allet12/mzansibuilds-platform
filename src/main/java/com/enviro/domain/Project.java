package com.enviro.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<CollaborationRequest> collaborationRequests;

    protected Project() {
    }

    public Project(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.createdAt = builder.createdAt;
        this.user = builder.user;
        this.posts = builder.posts;
        this.collaborationRequests = builder.collaborationRequests;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<CollaborationRequest> getCollaborationRequests() {
        return collaborationRequests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project project)) return false;

        if (getId() != null ? !getId().equals(project.getId()) : project.getId() != null) return false;
        if (getName() != null ? !getName().equals(project.getName()) : project.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(project.getDescription()) : project.getDescription() != null) return false;
        if (getCreatedAt() != null ? !getCreatedAt().equals(project.getCreatedAt()) : project.getCreatedAt() != null) return false;
        return getUser() != null ? getUser().equals(project.getUser()) : project.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", posts=" + posts +
                ", collaborationRequests=" + collaborationRequests +
                '}';
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private LocalDateTime createdAt;
        private User user;
        private List<Post> posts;
        private List<CollaborationRequest> collaborationRequests;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setPosts(List<Post> posts) {
            this.posts = posts;
            return this;
        }

        public Builder setCollaborationRequests(List<CollaborationRequest> collaborationRequests) {
            this.collaborationRequests = collaborationRequests;
            return this;
        }

        public Builder copy(Project project) {
            this.id = project.id;
            this.name = project.name;
            this.description = project.description;
            this.createdAt = project.createdAt;
            this.user = project.user;
            this.posts = project.posts;
            this.collaborationRequests = project.collaborationRequests;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }
}