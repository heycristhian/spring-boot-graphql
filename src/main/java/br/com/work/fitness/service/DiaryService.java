package br.com.work.fitness.service;

import br.com.work.fitness.model.Diary;
import br.com.work.fitness.model.User;
import br.com.work.fitness.model.domain.TotalCalorie;
import br.com.work.fitness.repository.DiaryRepository;
import br.com.work.fitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DiaryService implements iService<Diary> {

    @Autowired private DiaryRepository repository;
    @Autowired private UserRepository userRepository;

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

    public TotalCalorie totalCalorie(String userId, String date) {
        try {
            LocalDate dateDiary = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            User user = userRepository.findById(userId).get();
            List<Diary> diaries = repository.findByUser(user);

            BigDecimal calorie = diaries.stream()
                    .filter(diary -> diary.getUser().equals(user) && diary.getDate().equals(dateDiary))
                    .map(Diary::getCalories)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            TotalCalorie totalCalorie = TotalCalorie.builder()
                    .recommendedCalorie(user.getRecommendedCalories())
                    .total(calorie)
                    .msg("ok")
                    .build();
            return totalCalorie;

        } catch (NoSuchElementException e) {
            return totalCalorieException("User not found");
        } catch (DateTimeParseException e) {
            return totalCalorieException("The date field is not valid. Correct example -> 2020-01-01");
        }
    }

    private TotalCalorie totalCalorieException(String msg) {
        return TotalCalorie.builder()
                .total(BigDecimal.ZERO)
                .recommendedCalorie(BigDecimal.ZERO)
                .msg(msg)
                .build();
    }
}
