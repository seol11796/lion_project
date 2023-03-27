package com.example.lionPjt.answer;

import com.example.lionPjt.SiteUser.SiteUser;
import com.example.lionPjt.question.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime created;

    @ManyToOne
    private Question question;

    @ManyToOne
    private SiteUser author;
}