//***************************//
//* Project: Null Recipes
//        * Assignment: Assignment 1
//        * Author(s): Alberto Dos Reis
//        * Student Number: 101232584
//        * Date: October 26, 2021
//        * Description: Creates a contract with UserServiceImpl to use all of these methods from UserDetailsService and
//              and the CrudService.
//***************************//

package ca.gbc.comp3095.RecipeProject.services;

import ca.gbc.comp3095.RecipeProject.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService, CrudService<User, Long> {
    UserDetails loadUserByUsername(String username);
    User findUser();
    void setUser(User user);
    boolean userExistsUsername(String username);
    boolean userExistsEmail(String email);
    void setResetPasswordToken(String token, String email);
    void addToShoppingList(User user, String ingredient);
    User findUserByResetPasswordToken(String token);
}