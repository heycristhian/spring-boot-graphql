package br.com.work.fitness.model.domain;

import br.com.work.fitness.model.Diary;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DiaryInfo {

    private List<Diary> diaries;
    private DiaryDetail diaryDetail;
}
