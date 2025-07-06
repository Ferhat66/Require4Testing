package com.ferhatsertkaya.require4testing.repository;

import com.ferhatsertkaya.require4testing.model.Tester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesterRepository extends JpaRepository<Tester, Long> {
}