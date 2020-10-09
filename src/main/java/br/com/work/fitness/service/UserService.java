package br.com.work.fitness.service;

import br.com.work.fitness.model.User;
import br.com.work.fitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService implements iService<User>{

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        user.setRecommendedCalories(BigDecimal.valueOf(13.75).multiply(user.getWeight())
                .add(BigDecimal.valueOf(5).multiply(user.getHeight()).add(BigDecimal.valueOf(66.5))));

        user.setRecommendedCalories(user.getRecommendedCalories().subtract(BigDecimal.valueOf(6.76)
                .multiply(BigDecimal.valueOf(user.getAge()))));
        
        return repository.save(user);
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
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(String id) {
        return repository.findById(id).orElse(null);
    }
}
