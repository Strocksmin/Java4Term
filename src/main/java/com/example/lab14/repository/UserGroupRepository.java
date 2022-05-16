package com.example.lab14.repository;

import com.example.lab14.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    UserGroup findByCode(String code);
}
