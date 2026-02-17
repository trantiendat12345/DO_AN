package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.entity.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    @Query("SELECT s FROM Score s WHERE s.isDeleted = false")
    Page<Score> findAllScores(Pageable pageable);

    @Query("SELECT s FROM Score s WHERE s.id = :id AND s.isDeleted = false")
    Score findScoreById(@Param("id") Long id);

    @Query("SELECT s FROM Score s WHERE s.student.studentCode = :studentCode AND s.isDeleted = false")
    List<Score> findScoresByStudentCode(@Param("studentCode") String studentCode);

    @Query("SELECT s FROM Score s WHERE s.classroom.name = :classroomName AND s.isDeleted = false")
    List<Score> findScoresByClassroomName(@Param("classroomName") String classroomName);

    @Query("SELECT s FROM Score s WHERE s.student.studentCode = :studentCode AND s.classroom.name = :classroomName AND s.isDeleted = false")
    List<Score> findScoresByStudentCodeAndClassroomName(@Param("studentCode") String studentCode,
            @Param("classroomName") String classroomName);

    @Query("SELECT s FROM Score s WHERE s.classroom.name = :classroomName AND s.isDeleted = false")
    Page<Score> findScoresByClassroom(@Param("classroomName") String classroomName, Pageable pageable);
}
