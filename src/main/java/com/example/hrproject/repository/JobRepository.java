package com.example.hrproject.repository;

import com.example.hrproject.domain.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {
}
