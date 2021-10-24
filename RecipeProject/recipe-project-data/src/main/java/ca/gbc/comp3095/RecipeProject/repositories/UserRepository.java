package ca.gbc.comp3095.RecipeProject.repositories;

import ca.gbc.comp3095.RecipeProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
