package br.com.work.fitness.graphql;

import br.com.work.fitness.graphql.input.DiaryInput;
import br.com.work.fitness.graphql.input.UserInput;
import br.com.work.fitness.model.Diary;
import br.com.work.fitness.model.Menu;
import br.com.work.fitness.model.User;
import br.com.work.fitness.service.DiaryService;
import br.com.work.fitness.service.FoodService;
import br.com.work.fitness.service.UserService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiaryGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private DiaryService service;

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    public Diary saveDiary(DiaryInput input) {
        Diary diary = input.converter(userService, foodService);
        return service.save(diary);
    }

    public List<Diary> diaries() {
        return service.findAll();
    }

    public Diary diary(String id) {
        return service.findById(id);
    }

    public Boolean deleteDiary(String id) {
        return service.deleteById(id);
    }
}
