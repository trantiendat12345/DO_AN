package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.request.score.CreateScoreRequest;
import com.example.EduCenter_BE.request.score.UpdateScoreRequest;
import com.example.EduCenter_BE.response.ScoreResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScoreService {

    ScoreResponse createScore(CreateScoreRequest request);

    Page<ScoreResponse> getAllScores(Pageable pageable);

    ScoreResponse getScoreById(Long id);

    List<ScoreResponse> getScoresByStudentCode(String studentCode);

    Page<ScoreResponse> getScoresByClassroom(String classroomName, Pageable pageable);

    ScoreResponse updateScore(Long id, UpdateScoreRequest request);

    String deleteScore(Long id);
}
