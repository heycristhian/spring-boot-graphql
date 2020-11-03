package br.com.work.fitness.graphql.input;

import br.com.work.fitness.model.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;

@Data
public class UserInput {

    private String id;
    private String username;
    private String password;
    private Integer age;
    private BigDecimal height;
    private BigDecimal weight;

    public User converter() {
        this.password = new BCryptPasswordEncoder().encode(this.password);
        return new User(this.id, this.username, this.password, this.age, this.height, this.weight, null, null);
    }
}
