package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entity.Users;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

}
