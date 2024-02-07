package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents.Content;
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
import java.util.Optional;

@Service
public class ContestServices {

    @Autowired
    private AuthorizationRepository<Contest> contestRepository;

    @Autowired
    private RequestServices requestServices;

    @Autowired
    private POIServices poiServices;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthorizationRepository<POI> poiRepository;

    public void confirmNew(Contest contest, POI reference) throws Exception {
        Date creation = new Date(System.currentTimeMillis());
        AuthorizationRequest request = new AuthorizationRequest(contest.getAuthor(), contest, creation);
        requestServices.submit(request);
        reference.addContest(contest);
        poiServices.save(reference);
    }

    public void close(long idContest) {}

    public void delete(Contest contest) {
        contestRepository.delete(contest);
    }

    public List<Contest> getAllContests() {
        return contestRepository.findAll();
    }

    public Optional<Contest> getContest(long ID) {
        return contestRepository.findById(ID);
    }

    public List<Contest> getContests(long idPOI) {return poiRepository.findById(idPOI)
            .map(POI::getContests)
            .orElse(Collections.emptyList());
    }

    public void save(Contest contest) {
        contestRepository.save(contest);
    }
}