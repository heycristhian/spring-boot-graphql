package br.com.work.fitness.config;

import br.com.work.fitness.model.Food;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class Instantiation implements CommandLineRunner {



    @Override
    public void run(String... args) throws Exception {
        Food food = new Food();
        food.setName("Arroz");
        food.setPortion(BigDecimal.valueOf(100));
        food.setCarbohydrate(BigDecimal.valueOf(10));
        food.setProtein(BigDecimal.valueOf(3));
        System.out.println(food);
    }
}
