package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.Requests.MultiStatusRequest;
import it.unicam.cs.ids.TerraViva.Models.Requests.Request;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI;
import it.unicam.cs.ids.TerraViva.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthRequestServices {
    @Autowired
    private RequestRepository<AuthorizationRequest> authRequestRepository;

    public List<AuthorizationRequest> pendingRequests(){
        return authRequestRepository.findAll();
    }

    public void submit(AuthorizationRequest request){
        authRequestRepository.save(request);
    }

    public void accept(long ID) throws Exception {
        Optional<AuthorizationRequest> request = authRequestRepository.findById(ID);
        if(request.isPresent()) request.get().approve();
        else throw new Exception("No request with the given ID");
        authRequestRepository.save(request.get());
    }

    public void reject(long ID) throws Exception {
        Optional<AuthorizationRequest> request = authRequestRepository.findById(ID);
        if(request.isPresent()) request.get().reject();
        else throw new Exception("No request with the given ID");
        authRequestRepository.save(request.get());
    }

}
