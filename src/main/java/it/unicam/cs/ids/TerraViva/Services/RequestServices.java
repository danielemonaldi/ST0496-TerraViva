package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.Requests.MultiStatusRequest;
import it.unicam.cs.ids.TerraViva.Models.Requests.RequestStatus;
import it.unicam.cs.ids.TerraViva.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestServices {
    @Autowired
    private RequestRepository<MultiStatusRequest> requestRepository;

    public List<MultiStatusRequest> getRequests(){
        return requestRepository.findAll();
    }
    public Optional<MultiStatusRequest> getRequestsByStatus(RequestStatus status){
        return requestRepository.findByStatus(status);
    }

    public void submit(MultiStatusRequest request){
        requestRepository.save(request);
    }

    public void accept(MultiStatusRequest request) throws Exception {
        Optional<MultiStatusRequest> foundRequest = requestRepository.findById(request.getID());
        if(foundRequest.isPresent()) foundRequest.get().approve();
        else throw new Exception("No request with the given ID");
        requestRepository.save(foundRequest.get());
    }

    public void reject(MultiStatusRequest request) throws Exception {
        Optional<MultiStatusRequest> foundRequest = requestRepository.findById(request.getID());
        if(foundRequest.isPresent()) foundRequest.get().reject();
        else throw new Exception("No request with the given ID");
        requestRepository.save(foundRequest.get());
    }

}
