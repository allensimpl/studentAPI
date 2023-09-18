package com.studentPro.studentManager.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;
    @Column(name = "subject")
    String subject;

    public Subject() {
    }

    public Subject(int id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    public Subject(String subject) {
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

