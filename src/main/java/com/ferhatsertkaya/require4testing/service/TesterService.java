package com.ferhatsertkaya.require4testing.service;

import com.ferhatsertkaya.require4testing.model.Tester;

import java.util.List;
import java.util.Optional;

public interface TesterService {
    Tester saveTester(Tester tester);
    List<Tester> getAllTesters();
    Optional<Tester> getTesterById(Long id);
    void deleteTester(Long id);
}