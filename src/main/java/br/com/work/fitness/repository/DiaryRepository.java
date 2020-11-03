package br.com.work.fitness.repository;

import br.com.work.fitness.model.Diary;
import br.com.work.fitness.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiaryRepository extends MongoRepository<Diary, String> {

    List<Diary> findByUser(User user);
}
