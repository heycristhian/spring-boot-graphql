package br.com.work.fitness.model.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TotalCalorie {

    private BigDecimal total;
    private BigDecimal recommendedCalorie;
    private String date;
    private String msg;
}
