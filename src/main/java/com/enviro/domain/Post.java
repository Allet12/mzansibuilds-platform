package com.enviro.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    protected Post() {}

    public Post(Builder builder) {
        this.id = builder.id;
        this.author = builder.author;
        this.content = builder.content;
        this.createdAt = builder.createdAt;
        this.project = builder.project;
        this.comments = builder.comments;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public User getAuthor() { return author; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Project getProject() { return project; }
    public List<Comment> getComments() { return comments; }

    public void setAuthor(User author) { this.author = author; }
    public void setContent(String content) { this.content = content; }
    public void setProject(Project project) { this.project = project; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Post{id=" + id + ", content='" + content + "'}";
    }

    public static class Builder {
        private Long id;
        private User author;
        private String content;
        private LocalDateTime createdAt;
        private Project project;
        private List<Comment> comments;

        public Builder setId(Long id) { this.id = id; return this; }
        public Builder setAuthor(User author) { this.author = author; return this; }
        public Builder setContent(String content) { this.content = content; return this; }
        public Builder setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder setProject(Project project) { this.project = project; return this; }
        public Builder setComments(List<Comment> comments) { this.comments = comments; return this; }

        public Builder copy(Post post) {
            this.id = post.id;
            this.author = post.author;
            this.content = post.content;
            this.createdAt = post.createdAt;
            this.project = post.project;
            this.comments = post.comments;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}