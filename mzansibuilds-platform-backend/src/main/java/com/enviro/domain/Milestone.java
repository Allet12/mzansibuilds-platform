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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Milestone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    private String title;
    private String description;
    private LocalDateTime achievedAt;
    private boolean achieved;

    protected Milestone() {}

    public Milestone(Builder builder) {
        this.id = builder.id;
        this.project = builder.project;
        this.title = builder.title;
        this.description = builder.description;
        this.achievedAt = builder.achievedAt;
        this.achieved = builder.achieved;
    }

    @PrePersist
    protected void onCreate() {
        if (achieved && achievedAt == null) {
            this.achievedAt = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }
    public Project getProject() { return project; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDateTime getAchievedAt() { return achievedAt; }
    public boolean isAchieved() { return achieved; }

    public void setProject(Project project) { this.project = project; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setAchieved(boolean achieved) { this.achieved = achieved; }
    public void setAchievedAt(LocalDateTime achievedAt) { this.achievedAt = achievedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Milestone milestone)) return false;
        return id != null && id.equals(milestone.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Milestone{id=" + id + ", title='" + title + "'}";
    }

    public static class Builder {
        private Long id;
        private Project project;
        private String title;
        private String description;
        private LocalDateTime achievedAt;
        private boolean achieved;

        public Builder setId(Long id) { this.id = id; return this; }
        public Builder setProject(Project project) { this.project = project; return this; }
        public Builder setTitle(String title) { this.title = title; return this; }
        public Builder setDescription(String description) { this.description = description; return this; }
        public Builder setAchievedAt(LocalDateTime achievedAt) { this.achievedAt = achievedAt; return this; }
        public Builder setAchieved(boolean achieved) { this.achieved = achieved; return this; }

        public Builder copy(Milestone milestone) {
            this.id = milestone.id;
            this.project = milestone.project;
            this.title = milestone.title;
            this.description = milestone.description;
            this.achievedAt = milestone.achievedAt;
            this.achieved = milestone.achieved;
            return this;
        }

        public Milestone build() {
            return new Milestone(this);
        }
    }
}