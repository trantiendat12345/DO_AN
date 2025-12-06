package com.example.EduCenter_BE.entity;

import com.example.EduCenter_BE.constant.enums.ScoreType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "score")
@Getter
@Setter
@NoArgsConstructor
public class Score extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;

    @Enumerated(EnumType.STRING)
    @Column(name = "score_type", length = 30, nullable = false)
    private ScoreType type;

    @Column(name = "score_value", nullable = false)
    private Float value;

    @Column(name = "note", length = 255)
    private String note;
}
