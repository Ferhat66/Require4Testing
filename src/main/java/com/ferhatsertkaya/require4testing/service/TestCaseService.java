package com.ferhatsertkaya.require4testing.service;

import com.ferhatsertkaya.require4testing.model.TestCase;

import java.util.List;
import java.util.Optional;

public interface TestCaseService {
    TestCase saveTestCase(TestCase testCase);
    List<TestCase> getAllTestCases();
    Optional<TestCase> getTestCaseById(Long id);
    void deleteTestCase(Long id);
}