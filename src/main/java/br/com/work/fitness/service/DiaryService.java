package br.com.work.fitness.service;

import br.com.work.fitness.model.Diary;
import br.com.work.fitness.model.User;
import br.com.work.fitness.model.domain.DiaryDetail;
import br.com.work.fitness.model.domain.TotalCalorie;
import br.com.work.fitness.repository.DiaryRepository;
import br.com.work.fitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<TotalCalorie> totalCalorie(String userId) {
        User user = userRepository.findById(userId).get();
        List<Diary> diaries = repository.findByUser(user);
        List<TotalCalorie> totalCalories = new ArrayList<>();

        Set<LocalDate> diariesDate = diaries
                .stream()
                .map(Diary::getDate)
                .collect(Collectors.toSet());

        for (LocalDate date : diariesDate) {
            BigDecimal calories = diaries.stream()
                    .filter(diary -> diary.getDate().equals(date))
                    .map(Diary::getCalories)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            totalCalories.add(
                    TotalCalorie.builder()
                    .total(calories)
                    .recommendedCalorie(user.getRecommendedCalories())
                    .date(date.toString())
                    .msg("OK")
                    .build()
            );
        }

        return totalCalories;
    }

    public DiaryDetail diaryDetail(String userId, String dateString) {
        User user = userRepository.findById(userId).get();
        LocalDate date = LocalDate.parse(dateString);
        List<Diary> diaries = repository.findByUser(user);
        diaries = diaries.stream()
                .filter(diary -> diary.getDate().equals(date))
                .collect(Collectors.toList());

        return createDiaryDetail(diaries, date, user);
    }

    private DiaryDetail createDiaryDetail(List<Diary> diaries, LocalDate date, User user) {
        BigDecimal calorie = diaries.stream()
                .map(Diary::getCalories)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal carbohydrate = diaries.stream()
                .map(Diary::getCarbohydrate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal fat = diaries.stream()
                .map(Diary::getFat)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal protein = diaries.stream()
                .map(Diary::getProtein)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return DiaryDetail.builder()
                .calorie(calorie)
                .carbohydrate(carbohydrate)
                .fat(fat)
                .protein(protein)
                .date(date.toString())
                .recommendedCalorie(user.getRecommendedCalories())
                .build();
    }
}
