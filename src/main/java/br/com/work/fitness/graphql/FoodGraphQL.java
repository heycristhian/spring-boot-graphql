package br.com.work.fitness.graphql;

import br.com.work.fitness.graphql.input.FoodInput;
import br.com.work.fitness.model.Food;
import br.com.work.fitness.service.FoodService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private FoodService service;

    public Food saveFood(FoodInput input) {
        Food food = input.converter();
        return service.save(food);
    }

    public List<Food> foods() {
        return service.findAll();
    }

    public Food food(String id) {
        return service.findById(id);
    }
}
