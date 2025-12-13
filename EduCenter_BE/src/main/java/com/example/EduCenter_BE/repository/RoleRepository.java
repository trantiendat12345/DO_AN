package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("SELECT r FROM Role AS r WHERE r.id = :id AND r.isDeleted = false")
    Role findRoleById(@Param("id") Integer id);

    @Query("SELECT r FROM Role AS r WHERE r.name = :name AND r.isDeleted = false")
    Role findRoleByName(@Param("name") String name);

}
