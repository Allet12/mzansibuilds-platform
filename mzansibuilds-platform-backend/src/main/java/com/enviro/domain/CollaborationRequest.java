package com.enviro.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class CollaborationRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User requester;

    @ManyToOne
    private Project project;

    private String message;
    private LocalDateTime requestedAt;
    private boolean approved;

    protected CollaborationRequest() {
    }

    public CollaborationRequest(Builder builder) {
        this.id = builder.id;
        this.requester = builder.requester;
        this.project = builder.project;
        this.message = builder.message;
        this.requestedAt = builder.requestedAt;
        this.approved = builder.approved;
    }

    public Long getId() {
        return id;
    }

    public User getRequester() {
        return requester;
    }

    public Project getProject() {
        return project;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CollaborationRequest that)) return false;

        if (isApproved() != that.isApproved()) return false;
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getRequester() != null ? !getRequester().equals(that.getRequester()) : that.getRequester() != null) return false;
        if (getProject() != null ? !getProject().equals(that.getProject()) : that.getProject() != null) return false;
        if (getMessage() != null ? !getMessage().equals(that.getMessage()) : that.getMessage() != null) return false;
        return getRequestedAt() != null ? getRequestedAt().equals(that.getRequestedAt()) : that.getRequestedAt() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getRequester() != null ? getRequester().hashCode() : 0);
        result = 31 * result + (getProject() != null ? getProject().hashCode() : 0);
        result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
        result = 31 * result + (getRequestedAt() != null ? getRequestedAt().hashCode() : 0);
        result = 31 * result + (isApproved() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CollaborationRequest{" +
                "id=" + id +
                ", requester=" + requester +
                ", project=" + project +
                ", message='" + message + '\'' +
                ", requestedAt=" + requestedAt +
                ", approved=" + approved +
                '}';
    }

    public static class Builder {
        private Long id;
        private User requester;
        private Project project;
        private String message;
        private LocalDateTime requestedAt;
        private boolean approved;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setRequester(User requester) {
            this.requester = requester;
            return this;
        }

        public Builder setProject(Project project) {
            this.project = project;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setRequestedAt(LocalDateTime requestedAt) {
            this.requestedAt = requestedAt;
            return this;
        }

        public Builder setApproved(boolean approved) {
            this.approved = approved;
            return this;
        }

        public Builder copy(CollaborationRequest collaborationRequest) {
            this.id = collaborationRequest.id;
            this.requester = collaborationRequest.requester;
            this.project = collaborationRequest.project;
            this.message = collaborationRequest.message;
            this.requestedAt = collaborationRequest.requestedAt;
            this.approved = collaborationRequest.approved;
            return this;
        }

        public CollaborationRequest build() {
            return new CollaborationRequest(this);
        }
    }
}