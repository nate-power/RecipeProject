//***************************//
//* Project: Null Recipes
//        * Assignment: Assignment 2
//        * Author(s): Alberto Dos Reis
//        * Student Number: 101232584
//        * Date: November 24, 2021
//        * Description: This service will be recognized by Spring to provide the functionality for the
//              the methods contracted in the Service interface for EventService object and for the CrudService.
//***************************//

package ca.gbc.comp3095.RecipeProject.services;

import ca.gbc.comp3095.RecipeProject.models.Event;
import ca.gbc.comp3095.RecipeProject.models.User;
import ca.gbc.comp3095.RecipeProject.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event save(Event event) {
        eventRepository.save(event);
        return event;
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElse(null);
    }

    @Override
    public List<Event> findAllByUser(User user) {
        return eventRepository.findAllByUserOrderByDate(user);
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }
}
