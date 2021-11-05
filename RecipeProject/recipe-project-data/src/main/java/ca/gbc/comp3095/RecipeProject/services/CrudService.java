//*********************************************************************************
//* Project: < Null Recipes >
//        * Assignment: assignment #1
//        * Author(s): Justin Bartlett
//        * Student Number: 101246661
//        * Date: October 25 2021
//        * Description: This is the base CrudService which is extended by other services. It contains only basic
//                      functions, including saving and retrieving objects.
//*********************************************************************************

package ca.gbc.comp3095.RecipeProject.services;

import java.util.List;

public interface CrudService<T, ID> {
    T save(T object);
    List<T> findAll();
    T findById(ID id);
}