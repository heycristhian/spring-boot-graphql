package br.com.work.fitness.graphql.input;

import br.com.work.fitness.model.Food;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FoodInput {

    private String id;
    private String name;
    private BigDecimal protein;
    private BigDecimal carbohydrate;
    private BigDecimal fat;
    private BigDecimal portion;

    public Food converter() {
        return new Food(this.id, this.name, this.protein, this.carbohydrate, this.fat, this.portion);
    }
}
