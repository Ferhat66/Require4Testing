package com.ferhatsertkaya.require4testing.repository;

import com.ferhatsertkaya.require4testing.model.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Long> {
}