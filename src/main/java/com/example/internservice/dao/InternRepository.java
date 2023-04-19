package com.example.internservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.internservice.model.Intern;

@Repository
public interface InternRepository extends JpaRepository<Intern,Long> {
    List<Intern> findByAge(int age);
    Optional<Intern> findByEmail(String email);
    List<Intern> findByIsActiveFalse();
    Optional<Intern> findByActivationCode(String activationCode);
    
}
