package br.com.work.fitness.service;

import br.com.work.fitness.model.Diary;
import br.com.work.fitness.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService implements iService<Diary> {

    @Autowired
    private DiaryRepository repository;

    @Override
    public Diary save(Diary diary) {
        return repository.save(diary);
    }

    @Override
    public Boolean deleteById(String id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Diary> findAll() {
        return repository.findAll();
    }

    @Override
    public Diary findById(String id) {
        return repository.findById(id).orElse(null);
    }
}
