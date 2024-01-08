package it.unicam.cs.ids.TerraViva.Models.Services;

import it.unicam.cs.ids.TerraViva.Models.POI;
import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.Role;
import it.unicam.cs.ids.TerraViva.Models.User;
import it.unicam.cs.ids.TerraViva.Repository.POIRepository;
import it.unicam.cs.ids.TerraViva.Repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class POIServicesTest {

    @Mock
    private POIRepository poiRepository;

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private POIServices poiServices;

    @Test
    public void testCreate() throws Exception {
        // Mocking user data
        Role role = Role.CONTRIBUTOR; // Assuming Role is an enum with different roles
        User mockUser = new User("username", "password", "email@example.com", role); // Assuming a User class with appropriate constructor
        when(usersRepository.findByUsername("username")).thenReturn(Optional.of(mockUser));

        // Test data
        String name = "POIName";
        double latitude = 40.7128;
        double longitude = -74.0060;
        Date expire = null; // Your expiration date
        String author = "username";

        // Mocking POI creation
        when(poiRepository.save(any(POI.class))).thenAnswer(invocation -> {
            POI savedPOI = invocation.getArgument(0);
            savedPOI.setName(name); // Set other properties if needed
            return savedPOI;
        });

        // Call the method
        AuthorizationRequest request = poiServices.create(name, latitude, longitude, expire, author);

        // Assertions
        assertNotNull(request);
        assertEquals(mockUser, request.getAuthor());
        assertNotNull(request.getContent());
        assertNotNull(request.getCreationDate());
        assertTrue(request.getCreationDate().before(new Date()));
    }
}