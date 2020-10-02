package br.com.work.fitness.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diary {

    @Id
    private String id;
    private LocalDate date;
    private BigDecimal portion;
    private Food food;
    private BigDecimal calories;
    private Menu menu;
    private User user;
}
