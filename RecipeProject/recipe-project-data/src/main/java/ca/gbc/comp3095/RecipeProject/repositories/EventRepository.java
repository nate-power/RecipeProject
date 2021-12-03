//*********************************************************************************
//* Project: < Null Recipes >
//        * Assignment: assignment #2
//        * Author(s): Justin Bartlett
//        * Student Number: 101246661
//        * Date: November 20 2021
//        * Description: Event repository has one method, find all by user, that accesses the JPA repository to pull
//                      all users and orders them by the date created.
//*********************************************************************************

package ca.gbc.comp3095.RecipeProject.repositories;

import ca.gbc.comp3095.RecipeProject.models.Event;
import ca.gbc.comp3095.RecipeProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByUserOrderByDate(User user);
}
