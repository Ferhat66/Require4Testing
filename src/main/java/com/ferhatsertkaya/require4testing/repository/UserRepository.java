package com.ferhatsertkaya.require4testing.repository;

import com.ferhatsertkaya.require4testing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}