package ca.gbc.comp3095.RecipeProject.services;

import java.util.Set;

public interface CrudService<T, ID> {
    T save(T object);
    Iterable<T> findAll();
}