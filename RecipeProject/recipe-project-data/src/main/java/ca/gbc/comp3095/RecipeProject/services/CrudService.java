package ca.gbc.comp3095.RecipeProject.services;

import java.util.List;
import java.util.Set;


public interface CrudService<T, ID> {
    T save(T object);
    List<T> findAll();
    T findById(ID id);
}