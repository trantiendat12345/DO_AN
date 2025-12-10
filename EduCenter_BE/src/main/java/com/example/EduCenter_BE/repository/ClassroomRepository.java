package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    @Query("SELECT c FROM Classroom AS c WHERE c.name = :name")
    Classroom getClassroomByName(@Param("name") String name);

}
