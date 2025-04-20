package com.yourname.summariser.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "summary_records")
public class SummaryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(columnDefinition = "TEXT")
    private String summary;

    // Constructors
    public SummaryRecord() {}

    public SummaryRecord(String username, String summary) {
        this.username = username;
        this.summary = summary;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getSummary() { return summary; }

    public void setSummary(String summary) { this.summary = summary; }
}
