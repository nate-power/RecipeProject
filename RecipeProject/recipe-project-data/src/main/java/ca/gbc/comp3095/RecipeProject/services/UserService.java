package ca.gbc.comp3095.RecipeProject.services;

import ca.gbc.comp3095.RecipeProject.model.User;
import ca.gbc.comp3095.RecipeProject.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService, CrudService<User, Long> {
    UserDetails loadUserByUsername(String username);
    User findUser();
    boolean userExistsUsername(String username);
    boolean userExistsEmail(String email);
}
