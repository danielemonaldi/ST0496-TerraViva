package it.unicam.cs.ids.TerraViva.Repository;

import it.unicam.cs.ids.TerraViva.Models.Role;
import it.unicam.cs.ids.TerraViva.Models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@SpringJUnitConfig
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void testSaveUser() {

        // User creation
        User user = new User("mariorossi", "mariorossi123", "mario.rossi@gmail.com", Role.AUTHORIZED_TOURIST);

        // User saving
        usersRepository.save(user);

        // User retrieves
        User retrievedUser = usersRepository.findByUsername("mariorossi").orElse(null);

        // Verification
        assertNotNull(retrievedUser);
        assertEquals("mario.rossi@gmail.com", retrievedUser.getEmail());
    }

    @AfterEach
    public void cleaning() {
        // DB cleaning
        usersRepository.deleteAll();
    }
}