package com.enviro.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    private String text;
    private LocalDateTime createdAt;

    protected Comment() {
    }

    public Comment(Builder builder) {
        this.id = builder.id;
        this.author = builder.author;
        this.post = builder.post;
        this.text = builder.text;
        this.createdAt = builder.createdAt;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Post getPost() {
        return post;
    }

    public String getText() {
        return text;
    }

    public String getContent() {
        return text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;

        if (getId() != null ? !getId().equals(comment.getId()) : comment.getId() != null) return false;
        if (getAuthor() != null ? !getAuthor().equals(comment.getAuthor()) : comment.getAuthor() != null) return false;
        if (getPost() != null ? !getPost().equals(comment.getPost()) : comment.getPost() != null) return false;
        if (getText() != null ? !getText().equals(comment.getText()) : comment.getText() != null) return false;
        return getCreatedAt() != null ? getCreatedAt().equals(comment.getCreatedAt()) : comment.getCreatedAt() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getPost() != null ? getPost().hashCode() : 0);
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author=" + author +
                ", post=" + post +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public static class Builder {
        private Long id;
        private User author;
        private Post post;
        private String text;
        private LocalDateTime createdAt;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setAuthor(User author) {
            this.author = author;
            return this;
        }

        public Builder setPost(Post post) {
            this.post = post;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setContent(String content) {
            this.text = content;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder copy(Comment comment) {
            this.id = comment.id;
            this.author = comment.author;
            this.post = comment.post;
            this.text = comment.text;
            this.createdAt = comment.createdAt;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }
}