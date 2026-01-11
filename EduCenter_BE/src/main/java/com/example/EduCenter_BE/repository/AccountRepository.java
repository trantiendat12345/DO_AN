package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.constant.enums.UserType;
import com.example.EduCenter_BE.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account AS a WHERE a.username = :username AND a.isDeleted = false")
    Account findAccountByUsername(@Param("username") String username);

    @Query("SELECT COUNT(*) FROM Account AS a")
    long count();

    @Query("SELECT a FROM Account AS a WHERE a.userId = :userId AND a.type = :type")
    Account findAccountByUserIdAndType(@Param("userId") Long userId, @Param("type") UserType type);

}
