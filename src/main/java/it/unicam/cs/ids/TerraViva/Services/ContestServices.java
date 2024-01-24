package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contest;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.POI;
import it.unicam.cs.ids.TerraViva.Repository.AuthorizationRepository;
import it.unicam.cs.ids.TerraViva.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ContestServices {

    @Autowired
    private AuthorizationRepository<Contest> contestRepository;

    @Autowired
    private RequestServices requestServices;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthorizationRepository<POI> poiRepository;

    public void publish(Contest contest) throws Exception {

        if (usersRepository.findByUsername(contest.getAuthor().getUsername()).isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }

        Date creation = new Date(System.currentTimeMillis());
        AuthorizationRequest request = new AuthorizationRequest(contest.getAuthor(), contest, creation);
        contestRepository.save(contest);
        requestServices.submit(request);
    }

    public void close(long idContest) {}

    public void delete(long idContest) throws Exception {

        if (contestRepository.existsById(idContest)) {
            contestRepository.deleteById(idContest);
        } else {
            throw new Exception("Contest not found with ID: " + idContest);
        }
    }

    public List<Contest> getContest(long idPOI) {return poiRepository.findById(idPOI)
            .map(POI::getContests)
            .orElse(Collections.emptyList());
    }
}