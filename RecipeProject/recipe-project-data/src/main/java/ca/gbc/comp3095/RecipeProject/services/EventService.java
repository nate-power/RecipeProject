//***************************//
//* Project: Null Recipes
//        * Assignment: Assignment 2
//        * Author(s): Alberto Dos Reis
//        * Student Number: 101232584
//        * Date: November 24, 2021
//        * Description: Creates a contract with EventServiceImpl to use all of these methods from repository and
//              and the CrudService. Also contains a delete method to that deletes event by passing the ID.
//***************************//

package ca.gbc.comp3095.RecipeProject.services;

import ca.gbc.comp3095.RecipeProject.models.Event;
import ca.gbc.comp3095.RecipeProject.models.User;

import java.util.List;

public interface EventService extends CrudService<Event, Long>{
    List<Event> findAllByUser(User user);
    void deleteById(Long id);
}
