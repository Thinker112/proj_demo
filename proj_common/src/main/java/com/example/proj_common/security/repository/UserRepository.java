package com.example.proj_common.security.repository;

import com.example.proj_common.security.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
