package com.example.task.repo;

import com.example.task.model.Number;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumberRepo extends JpaRepository<Number, Long> {

}
