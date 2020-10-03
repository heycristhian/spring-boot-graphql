package br.com.work.fitness.graphql.input;

import br.com.work.fitness.model.Diary;
import br.com.work.fitness.model.Food;
import br.com.work.fitness.model.Menu;
import br.com.work.fitness.model.User;
import br.com.work.fitness.service.FoodService;
import br.com.work.fitness.service.UserService;
import com.fasterxml.jackson.core.io.DataOutputAsStream;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DiaryInput {

    private String id;
    private String date;
    private BigDecimal portion;
    private String foodId;
    private Menu menu;
    private String userId;

    static final BigDecimal CARBOHYDRATE_MULTIPLY = BigDecimal.valueOf(4);
    static final BigDecimal PROTEIN_MULTIPLY = BigDecimal.valueOf(4);
    static final BigDecimal FAT_MULTIPLY = BigDecimal.valueOf(9);

    public Diary converter(UserService userService, FoodService foodService) {
        Diary diary = new Diary();
        totalCaloriesByDiary(userService, foodService, diary);
        return diary;
    }

    public BigDecimal totalCaloriesByDiary(UserService userService, FoodService foodService, Diary diary) {
        BigDecimal calories = BigDecimal.ZERO;
        User user = userService.findById(this.getUserId());
        Food food = foodService.findById(this.getFoodId());
        diary.setDate(LocalDate.now());
        diary.setPortion(this.portion);
        diary.setFood(food);
        diary.setUser(user);
        diary.setMenu(this.menu);
        diary.setProtein(food.getProtein().divide(food.getPortion()).multiply(diary.getPortion()));
        diary.setCarbohydrate(food.getCarbohydrate().divide(food.getPortion()).multiply(diary.getPortion()));
        diary.setFat(food.getFat().divide(food.getPortion()).multiply(diary.getPortion()));
        diary.setCalories(diary.getProtein().multiply(DiaryInput.PROTEIN_MULTIPLY)
                .add(diary.getCarbohydrate().multiply(DiaryInput.CARBOHYDRATE_MULTIPLY)
                        .add(diary.getFat().multiply(DiaryInput.FAT_MULTIPLY))));
        return calories;
    }
}
