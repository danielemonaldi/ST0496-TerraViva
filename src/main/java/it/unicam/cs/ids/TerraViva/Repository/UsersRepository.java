package it.unicam.cs.ids.TerraViva.Repository;

import it.unicam.cs.ids.TerraViva.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, String> {

    /*
     * Retrieves an {@code Optional<User>} based on the provides {@code username}.
     *
     * This method searches for a user entity in the database whose username matches
     * the specified value. If a user with the given username is found, the method
     * returns an {@code Optional} containing the corresponding {@code User} instance.
     * If no user is found, an empty {@code Optional} is returned.
     *
     * @param username The username to search for in the database.
     * @return An {@code Optional<User>} containing the user with the specified username
     *         if found, or an empty {@code Optional} if no user matches the given username.
     */
    Optional<User> findByUsername(String username);
}