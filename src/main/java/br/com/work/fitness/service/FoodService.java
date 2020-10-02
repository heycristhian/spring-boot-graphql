package br.com.work.fitness.service;

import br.com.work.fitness.model.Food;
import br.com.work.fitness.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService implements iService<Food>{

    @Autowired
    private FoodRepository repository;

    @Override
    public Food save(Food food) {
        return repository.save(food);
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
    public List<Food> findAll() {
        return repository.findAll();
    }

    @Override
    public Food findById(String id) {
        return repository.findById(id).orElse(null);
    }
}
