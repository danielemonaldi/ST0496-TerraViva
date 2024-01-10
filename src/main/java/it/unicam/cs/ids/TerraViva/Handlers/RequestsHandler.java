package it.unicam.cs.ids.TerraViva.Handlers;

import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.Requests.MultiStatusRequest;
import it.unicam.cs.ids.TerraViva.Models.Requests.Request;
import it.unicam.cs.ids.TerraViva.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestsHandler {
    private List<MultiStatusRequest> pendingList;
    private static RequestsHandler instance;

    @Autowired
    private RequestRepository<AuthorizationRequest> authRequestRepository;

    private RequestsHandler() {
        this.pendingList = new ArrayList<>();
    }

    public static RequestsHandler instance(){
        if (instance == null) {
            instance = new RequestsHandler();
        }
        return instance;
    }

    public List<MultiStatusRequest> pendingRequests(){
        return pendingList;
    }
    public void setRequests(List<MultiStatusRequest> requests){
        this.pendingList = requests;
    }

    public void submit(MultiStatusRequest request){
        if(request instanceof AuthorizationRequest) authRequestRepository.save((AuthorizationRequest) request);
        pendingList.add(request);
    }

    public void accept(long ID){
        Optional<MultiStatusRequest> request = pendingList.stream().filter(req -> req.getID() == ID).findFirst();
        request.ifPresent(Request::approve);
    }

    public void reject(long ID){
        Optional<MultiStatusRequest> request = pendingList.stream().filter(req -> req.getID() == ID).findFirst();
        request.ifPresent(Request::reject);
    }

}
