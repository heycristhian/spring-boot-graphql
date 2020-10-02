package br.com.work.fitness.graphql;

import br.com.work.fitness.graphql.input.FoodInput;
import br.com.work.fitness.graphql.input.UserInput;
import br.com.work.fitness.model.Food;
import br.com.work.fitness.model.User;
import br.com.work.fitness.service.FoodService;
import br.com.work.fitness.service.UserService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private UserService service;

    public User saveUser(UserInput input) {
        User user = input.converter();
        return service.save(user);
    }

    public List<User> users() {
        return service.findAll();
    }

    public User user(String id) {
        return service.findById(id);
    }
}
