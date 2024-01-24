package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.Role;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contest;
import it.unicam.cs.ids.TerraViva.Models.User;
import it.unicam.cs.ids.TerraViva.Repository.AuthorizationRepository;
import it.unicam.cs.ids.TerraViva.Repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContestServicesTest {

    @Autowired
    private ContestServices contestServices;

    @Autowired
    private AuthorizationRepository<Contest> contestRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @DirtiesContext
    @Transactional
    public void testPublish() throws Exception {

        User author = new User("testUser", "password", "test@example.com", Role.ENTERTAINER);
        usersRepository.save(author);

        Contest contest = new Contest("Test Contest", "Theme", "Rules", "Criteria",
                new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000), author);

        contestServices.publish(contest);

        assertTrue(contestRepository.findById(contest.getID()).isPresent());
    }

    @Test
    @DirtiesContext
    @Transactional
    public void testDelete() throws Exception {

        User author = new User("testUser", "password", "test@example.com", Role.ENTERTAINER);
        usersRepository.save(author);

        Contest contest = new Contest("Test Contest", "Theme", "Rules", "Criteria",
                new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000), author);

        contestServices.publish(contest);
        contestServices.delete(contest.getID());

        assertTrue(contestRepository.findById(contest.getID()).isEmpty());
    }
}