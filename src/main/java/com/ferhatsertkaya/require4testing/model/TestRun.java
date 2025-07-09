package com.ferhatsertkaya.require4testing.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class TestRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Ausführungsdatum darf nicht null sein")
    private LocalDateTime runDate;

    @NotBlank(message = "Status darf nicht leer sein")
    @Size(max = 100, message = "Status darf max. 100 Zeichen lang sein")
    private String status;

    @ManyToOne
    @JoinColumn(name = "test_case_id")
    @JsonBackReference // Verhindert Endlosschleife bei JSON-Serialisierung
    private TestCase testCase;

    @ManyToOne
    @JoinColumn(name = "tester_id")
    @JsonBackReference
    private Tester tester;

    public TestRun() {}

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getRunDate() { return runDate; }
    public void setRunDate(LocalDateTime runDate) { this.runDate = runDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public TestCase getTestCase() { return testCase; }
    public void setTestCase(TestCase testCase) { this.testCase = testCase; }

    public Tester getTester() { return tester; }
    public void setTester(Tester tester) { this.tester = tester; }
}