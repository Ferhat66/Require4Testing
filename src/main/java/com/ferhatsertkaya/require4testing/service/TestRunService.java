package com.ferhatsertkaya.require4testing.service;

import com.ferhatsertkaya.require4testing.model.TestRun;

import java.util.List;
import java.util.Optional;

public interface TestRunService {
    TestRun saveTestRun(TestRun testRun);
    List<TestRun> getAllTestRuns();
    Optional<TestRun> getTestRunById(Long id);
    void deleteTestRun(Long id);
}