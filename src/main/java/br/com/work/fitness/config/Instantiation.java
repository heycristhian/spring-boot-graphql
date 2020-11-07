package br.com.work.fitness.config;

import br.com.work.fitness.graphql.input.DiaryInput;
import br.com.work.fitness.model.Food;
import br.com.work.fitness.model.User;
import br.com.work.fitness.service.FoodService;
import br.com.work.fitness.service.UserService;
import kotlin.collections.ArrayDeque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {

        if (foodService.findAll().isEmpty()) {

            User user = new User();
            user.setUsername("admin");
            user.setPassword("$2a$10$JkMVhuu5EvKGQXBLDex54.0l5CZG.P8pnhJBu0xSylrWWukyLvGjy");
            user.setAge(25);
            user.setHeight(BigDecimal.valueOf(190));
            user.setWeight(BigDecimal.valueOf(90));
            userService.save(user);

            User user2 = new User();
            user2.setUsername("heycristhian");
            user2.setPassword("$2a$10$JkMVhuu5EvKGQXBLDex54.0l5CZG.P8pnhJBu0xSylrWWukyLvGjy");
            user2.setAge(25);
            user2.setHeight(BigDecimal.valueOf(190));
            user2.setWeight(BigDecimal.valueOf(90));
            userService.save(user2);

            Food food = new Food();
            food.setName("Arroz Cozido");
            food.setPortion(BigDecimal.valueOf(100));
            food.setCarbohydrate(BigDecimal.valueOf(28.1));
            food.setProtein(BigDecimal.valueOf(2.5));
            food.setFat(BigDecimal.valueOf(0.2));
            foodService.save(food);

            food = new Food();
            food.setName("Feijão Cozido");
            food.setPortion(BigDecimal.valueOf(100));
            food.setCarbohydrate(BigDecimal.valueOf(13.6));
            food.setProtein(BigDecimal.valueOf(4.8));
            food.setFat(BigDecimal.valueOf(0.5));
            foodService.save(food);

            food = new Food();
            food.setName("Pão Francês");
            food.setPortion(BigDecimal.valueOf(100));
            food.setCarbohydrate(BigDecimal.valueOf(58.60));
            food.setProtein(BigDecimal.valueOf(8));
            food.setFat(BigDecimal.valueOf(3.1));
            foodService.save(food);

            food = new Food();
            food.setName("Leite UHT Integral");
            food.setPortion(BigDecimal.valueOf(200));
            food.setCarbohydrate(BigDecimal.valueOf(10));
            food.setProtein(BigDecimal.valueOf(5.8));
            food.setFat(BigDecimal.valueOf(6));
            foodService.save(food);

            food = new Food();
            food.setName("Achocolatado em Pó");
            food.setPortion(BigDecimal.valueOf(20));
            food.setCarbohydrate(BigDecimal.valueOf(18));
            food.setProtein(BigDecimal.valueOf(0.5));
            food.setFat(BigDecimal.valueOf(0.5));
            foodService.save(food);

            food = new Food();
            food.setName("Peito de Frango");
            food.setPortion(BigDecimal.valueOf(100));
            food.setCarbohydrate(BigDecimal.valueOf(0));
            food.setProtein(BigDecimal.valueOf(32));
            food.setFat(BigDecimal.valueOf(2.1));
            foodService.save(food);

            food = new Food();
            food.setName("Coxão Duro");
            food.setPortion(BigDecimal.valueOf(100));
            food.setCarbohydrate(BigDecimal.valueOf(0));
            food.setProtein(BigDecimal.valueOf(31.90));
            food.setFat(BigDecimal.valueOf(8.9));
            foodService.save(food);
        }

    }
}
