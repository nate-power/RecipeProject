//*********************************************************************************
//* Project: < Null Recipes >
//        * Assignment: assignment #2
//        * Author(s): Justin Bartlett
//        * Student Number: 101246661
//        * Date: November 24 2021
//        * Description: This controller handles routing to pages related to adding, editing, and deleting events.
//                      It also interacts with the eventService to handle CRUD operations related to events.
//*********************************************************************************

package ca.gbc.comp3095.RecipeProject.controllers;

import ca.gbc.comp3095.RecipeProject.models.Event;
import ca.gbc.comp3095.RecipeProject.services.EventService;
import ca.gbc.comp3095.RecipeProject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class EventController {
    private final EventService eventService;
    private final UserService userService;

    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/event/add")
    public String addEvent(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("eventAction", "Add");
        return "/user/events/add";
    }

    @GetMapping("/event/edit/{id}")
    public String editEvent(Model model, @PathVariable Long id) {
        if (eventService.findAllByUser(userService.findUser()).contains(eventService.findById(id))) {
            model.addAttribute("event", eventService.findById(id));
            model.addAttribute("eventAction", "Edit");
            return "/user/events/add";
        }
        return "errors/error-404";
    }

    @PostMapping("/event/add")
    public String addEvent(@ModelAttribute("event") @Valid Event event, BindingResult result,
                           @RequestParam("eventAction") String eventAction, Model model) {
        if (event.getDate().isBefore(LocalDate.now())) {
            result.addError(new FieldError("event", "date", "Event cannot be scheduled" +
                    " for a date that has already passed."));
        }
        if (result.hasErrors()) {
            model.addAttribute("eventAction", eventAction);
            return "/user/events/add";
        }
        event.setUser(userService.findUser());
        eventService.save(event);
        return "redirect:/profile/" + userService.findUser().getUsername();
    }

    @PostMapping("event/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
        return "redirect:/profile/" + userService.findUser().getUsername();
    }
}
