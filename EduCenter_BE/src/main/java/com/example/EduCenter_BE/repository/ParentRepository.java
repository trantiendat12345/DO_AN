package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    @Query("SELECT p FROM Parent AS p WHERE p.email = :email")
    Parent findParentByEmail(@Param("email") String email);

    @Query("SELECT p FROM Parent AS p WHERE p.phone = :phone")
    Parent findParentByPhone(@Param("phone") String phone);

}
