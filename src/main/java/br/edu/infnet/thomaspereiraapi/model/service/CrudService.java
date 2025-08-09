package br.edu.infnet.thomaspereiraapi.model.service;

import java.util.List;

public interface CrudService<T, ID> {

    T add(T entity);
    T update(T entity);
    T delete(ID id);
    T getById(ID id);
    List<T> getList();
}
