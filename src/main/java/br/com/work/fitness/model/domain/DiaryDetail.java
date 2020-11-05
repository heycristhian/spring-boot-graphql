package br.com.work.fitness.model.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DiaryDetail {

    private String date;
    private BigDecimal protein;
    private BigDecimal carbohydrate;
    private BigDecimal fat;
    private BigDecimal calorie;
    private BigDecimal recommendedCalorie;
}
