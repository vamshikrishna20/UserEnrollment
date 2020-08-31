package com.healthcare.enrollment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.enrollment.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String name);

}
