package it.unicam.cs.ids.TerraViva.Repository;

import it.unicam.cs.ids.TerraViva.Models.Role;
import it.unicam.cs.ids.TerraViva.Models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@SpringJUnitConfig
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @DirtiesContext
    @Transactional
    public void testSaveUser() {

        // User creation
        User user = new User("testUser", "password", "test@example.com", Role.AUTHORIZED_TOURIST);

        // User saving
        usersRepository.save(user);

        // User retrieves
        User retrievedUser = usersRepository.findByUsername("testUser").orElse(null);

        // Verification
        assertNotNull(retrievedUser);
        assertEquals("test@example.com", retrievedUser.getEmail());
    }
}