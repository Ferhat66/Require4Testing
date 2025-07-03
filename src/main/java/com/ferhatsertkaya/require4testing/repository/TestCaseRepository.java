package com.ferhatsertkaya.require4testing.repository;

import com.ferhatsertkaya.require4testing.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
}