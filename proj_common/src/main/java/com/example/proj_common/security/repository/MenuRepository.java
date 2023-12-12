package com.example.proj_common.security.repository;

import com.example.proj_common.security.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}