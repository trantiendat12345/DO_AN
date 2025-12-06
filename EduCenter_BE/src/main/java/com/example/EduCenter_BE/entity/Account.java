package com.example.EduCenter_BE.entity;

import com.example.EduCenter_BE.constant.enums.AccountStatus;
import com.example.EduCenter_BE.constant.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@NoArgsConstructor
@Getter
@Setter
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", length = 30, nullable = false)
    private UserType type;

    @Column(name = "user_id", nullable = false)
    private Long userId; // Liên kết đến Student / Teacher / Parent (tuỳ type)

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private AccountStatus status = AccountStatus.ACTIVE;

}
