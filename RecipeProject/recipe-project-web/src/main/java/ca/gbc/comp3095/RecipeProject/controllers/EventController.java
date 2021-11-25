package ca.gbc.comp3095.RecipeProject.controllers;

import ca.gbc.comp3095.RecipeProject.models.Event;
import ca.gbc.comp3095.RecipeProject.services.EventService;
import ca.gbc.comp3095.RecipeProject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/event/add")
    public String addEvent(@ModelAttribute("event") @Valid Event event, BindingResult result, RedirectAttributes attributes) {
        if (event.getDate().isBefore(LocalDate.now())) {
            result.addError(new FieldError("event", "date", "Event cannot be scheduled" +
                    " for a date that has already passed."));
        }
        if (result.hasErrors()) {
            attributes.addFlashAttribute("errors", true);
            return "redirect:/profile/" + userService.findUser().getUsername();
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
