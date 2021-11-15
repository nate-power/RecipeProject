package ca.gbc.comp3095.RecipeProject.services;

import ca.gbc.comp3095.RecipeProject.models.Event;
import ca.gbc.comp3095.RecipeProject.models.User;

import java.util.List;

public interface EventService extends CrudService<Event, Long>{
    List<Event> findAllByUser(User user);
    void deleteById(Long id);
}
