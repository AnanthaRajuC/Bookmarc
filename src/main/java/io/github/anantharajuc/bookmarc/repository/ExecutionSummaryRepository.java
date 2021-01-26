package io.github.anantharajuc.bookmarc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.bookmarc.model.ExecutionSummary;

@Repository
public interface ExecutionSummaryRepository extends JpaRepository<ExecutionSummary, Integer>
{

}
