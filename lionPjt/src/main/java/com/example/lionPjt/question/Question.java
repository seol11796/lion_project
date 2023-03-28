package com.example.lionPjt.question;

import com.example.lionPjt.SiteUser.SiteUser;
import com.example.lionPjt.answer.Answer;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime created;

    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList = new ArrayList<>();

    // 추천인 (voter) , List가 아닌 Set으로 한 이유 - 추천인은 중복되면 x
    @ManyToOne
    private SiteUser author;


    @ManyToMany
    Set<SiteUser> voter;


    public void addAnswer(Answer a) {
        a.setQuestion(this);
        answerList.add(a);
    }
}