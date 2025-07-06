package com.ferhatsertkaya.require4testing.repository;

import com.ferhatsertkaya.require4testing.model.TestRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRunRepository extends JpaRepository<TestRun, Long> {
}