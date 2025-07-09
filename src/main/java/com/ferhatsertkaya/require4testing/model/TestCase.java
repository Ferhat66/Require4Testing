package com.ferhatsertkaya.require4testing.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Titel darf nicht leer sein")
    @Size(max = 255, message = "Titel darf max. 255 Zeichen lang sein")
    private String title;

    @Size(max = 2000, message = "Beschreibung darf max. 2000 Zeichen lang sein")
    private String description;

    @ManyToOne
    @JoinColumn(name = "requirement_id")
    @JsonBackReference 
    private Requirement requirement;

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Requirement getRequirement() { return requirement; }
    public void setRequirement(Requirement requirement) { this.requirement = requirement; }
}