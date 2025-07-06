package com.ferhatsertkaya.require4testing.service;

import com.ferhatsertkaya.require4testing.model.TestRun;
import com.ferhatsertkaya.require4testing.repository.TestRunRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestRunServiceImpl implements TestRunService {

    private final TestRunRepository testRunRepository;

    public TestRunServiceImpl(TestRunRepository testRunRepository) {
        this.testRunRepository = testRunRepository;
    }

    @Override
    public TestRun saveTestRun(TestRun testRun) {
        return testRunRepository.save(testRun);
    }

    @Override
    public List<TestRun> getAllTestRuns() {
        return testRunRepository.findAll();
    }

    @Override
    public Optional<TestRun> getTestRunById(Long id) {
        return testRunRepository.findById(id);
    }

    @Override
    public void deleteTestRun(Long id) {
        testRunRepository.deleteById(id);
    }
}