package br.com.work.fitness.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Menu {
    BREAKFAST("Café da manhã"),
    LUNCH("Almoço"),
    SNACK("Lanche"),
    DINNER("Janta");

    private String description;
}
