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

    @ManyToOne
    private User author;

    private String content;
    private LocalDateTime createdAt;

    @ManyToOne
    private Project project;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    protected Post() {
    }

    public Post(Builder builder) {
        this.id = builder.id;
        this.author = builder.author;
        this.content = builder.content;
        this.createdAt = builder.createdAt;
        this.project = builder.project;
        this.comments = builder.comments;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Project getProject() {
        return project;
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;

        if (getId() != null ? !getId().equals(post.getId()) : post.getId() != null) return false;
        if (getAuthor() != null ? !getAuthor().equals(post.getAuthor()) : post.getAuthor() != null) return false;
        if (getContent() != null ? !getContent().equals(post.getContent()) : post.getContent() != null) return false;
        if (getCreatedAt() != null ? !getCreatedAt().equals(post.getCreatedAt()) : post.getCreatedAt() != null) return false;
        return getProject() != null ? getProject().equals(post.getProject()) : post.getProject() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
        result = 31 * result + (getProject() != null ? getProject().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author=" + author +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", project=" + project +
                ", comments=" + comments +
                '}';
    }

    public static class Builder {
        private Long id;
        private User author;
        private String content;
        private LocalDateTime createdAt;
        private Project project;
        private List<Comment> comments;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setAuthor(User author) {
            this.author = author;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setProject(Project project) {
            this.project = project;
            return this;
        }

        public Builder setComments(List<Comment> comments) {
            this.comments = comments;
            return this;
        }

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