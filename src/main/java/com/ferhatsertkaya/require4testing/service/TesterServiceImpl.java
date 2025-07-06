package com.ferhatsertkaya.require4testing.service;

import com.ferhatsertkaya.require4testing.model.Tester;
import com.ferhatsertkaya.require4testing.repository.TesterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TesterServiceImpl implements TesterService {

    private final TesterRepository testerRepository;

    public TesterServiceImpl(TesterRepository testerRepository) {
        this.testerRepository = testerRepository;
    }

    @Override
    public Tester saveTester(Tester tester) {
        return testerRepository.save(tester);
    }

    @Override
    public List<Tester> getAllTesters() {
        return testerRepository.findAll();
    }

    @Override
    public Optional<Tester> getTesterById(Long id) {
        return testerRepository.findById(id);
    }

    @Override
    public void deleteTester(Long id) {
        testerRepository.deleteById(id);
    }
}