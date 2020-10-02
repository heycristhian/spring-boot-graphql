package br.com.work.fitness.service;

import java.util.List;

public interface iService<T> {
    public T save(T t);
    public Boolean deleteById(String id);
    public List<T> findAll();
    public T findById(String id);
}
