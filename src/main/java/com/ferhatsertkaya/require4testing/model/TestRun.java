package com.ferhatsertkaya.require4testing.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TestRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime runDate;

    @ManyToOne
    @JoinColumn(name = "tester_id")
    private Tester tester;

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getRunDate() { return runDate; }
    public void setRunDate(LocalDateTime runDate) { this.runDate = runDate; }

    public Tester getTester() { return tester; }
    public void setTester(Tester tester) { this.tester = tester; }
}