package com.example.proj_common.security.repository;

import com.example.proj_common.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
