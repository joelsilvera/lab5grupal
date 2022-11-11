package com.example.lab5grupal.repository;

import com.example.lab5grupal.entity.Department;
import com.example.lab5grupal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,String> {

    @Query(nativeQuery = true, value = "select * from jobs")
    List<Job> obtenerjobs();

}
