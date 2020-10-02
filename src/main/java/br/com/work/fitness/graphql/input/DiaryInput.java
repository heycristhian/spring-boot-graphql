package br.com.work.fitness.graphql.input;

import br.com.work.fitness.model.Diary;
import br.com.work.fitness.model.Food;
import br.com.work.fitness.model.Menu;
import br.com.work.fitness.model.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DiaryInput {

    private String id;
    private LocalDate date;
    private BigDecimal portion;
    private Food food;
    private BigDecimal calories;
    private Menu menu;
    private User user;

    public Diary converter() {
        return new Diary(this.id, this.date, this.portion, this.food, this.calories, this.menu, this.user);
    }
}
