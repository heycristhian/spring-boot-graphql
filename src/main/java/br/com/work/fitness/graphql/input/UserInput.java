package br.com.work.fitness.graphql.input;

import br.com.work.fitness.model.Food;
import br.com.work.fitness.model.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserInput {

    private String id;
    private String user;
    private String password;
    private BigDecimal height;
    private BigDecimal weight;

    public User converter() {
        return new User(this.id, this.user, this.password, this.height, this.weight);
    }
}
