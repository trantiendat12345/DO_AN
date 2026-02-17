package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.Classroom;
import com.example.EduCenter_BE.entity.Score;
import com.example.EduCenter_BE.entity.Student;
import com.example.EduCenter_BE.exception.BusinessException;
import com.example.EduCenter_BE.repository.ClassroomRepository;
import com.example.EduCenter_BE.repository.ScoreRepository;
import com.example.EduCenter_BE.repository.StudentRepository;
import com.example.EduCenter_BE.request.score.CreateScoreRequest;
import com.example.EduCenter_BE.request.score.UpdateScoreRequest;
import com.example.EduCenter_BE.response.ScoreResponse;
import com.example.EduCenter_BE.service.interfaces.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Override
    public ScoreResponse createScore(CreateScoreRequest request) {
        Student student = studentRepository.findStudentByCode(request.getStudentCode());
        Classroom classroom = classroomRepository.findClassroomByName(request.getClassroomName());

        if (Objects.isNull(student)) {
            throw new BusinessException(Message.STUDENT_DOES_NOT_EXIST);
        }

        if (Objects.isNull(classroom)) {
            throw new BusinessException(Message.CLASSROOM_DOES_NOT_EXIST);
        }

        Score score = new Score();
        score.setStudent(student);
        score.setClassroom(classroom);
        score.setType(request.getType());
        score.setValue(request.getValue());
        score.setNote(request.getNote());

        Score savedScore = scoreRepository.save(score);
        return new ScoreResponse(savedScore);
    }

    @Override
    public Page<ScoreResponse> getAllScores(Pageable pageable) {
        return scoreRepository.findAllScores(pageable).map(ScoreResponse::new);
    }

    @Override
    public ScoreResponse getScoreById(Long id) {
        Score score = scoreRepository.findScoreById(id);

        if (Objects.isNull(score)) {
            throw new BusinessException(Message.SCORE_DOES_NOT_EXIST);
        }

        return new ScoreResponse(score);
    }

    @Override
    public List<ScoreResponse> getScoresByStudentCode(String studentCode) {
        List<Score> scores = scoreRepository.findScoresByStudentCode(studentCode);
        return scores.stream().map(ScoreResponse::new).collect(Collectors.toList());
    }

    @Override
    public Page<ScoreResponse> getScoresByClassroom(String classroomName, Pageable pageable) {
        return scoreRepository.findScoresByClassroom(classroomName, pageable).map(ScoreResponse::new);
    }

    @Override
    public ScoreResponse updateScore(Long id, UpdateScoreRequest request) {
        Score score = scoreRepository.findScoreById(id);

        if (Objects.isNull(score)) {
            throw new BusinessException(Message.SCORE_DOES_NOT_EXIST);
        }

        score.setType(request.getType());
        score.setValue(request.getValue());
        score.setNote(request.getNote());
        score.setUpdatedBy(1L);

        Score updatedScore = scoreRepository.save(score);
        return new ScoreResponse(updatedScore);
    }

    @Override
    public String deleteScore(Long id) {
        Score score = scoreRepository.findScoreById(id);

        if (Objects.isNull(score)) {
            throw new BusinessException(Message.SCORE_DOES_NOT_EXIST);
        }

        score.setIsDeleted(true);
        score.setDeletedAt(LocalDateTime.now());
        score.setDeletedBy(1L);
        scoreRepository.save(score);

        return Message.DELETED_SUCCESSFULLY;
    }
}
