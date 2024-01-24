package it.unicam.cs.ids.TerraViva.Repository;

import it.unicam.cs.ids.TerraViva.Models.Requests.MultiStatusRequest;
import it.unicam.cs.ids.TerraViva.Models.Requests.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestRepository<T extends MultiStatusRequest> extends JpaRepository<T, Long> {
    Optional<MultiStatusRequest> findByStatus(RequestStatus status);
}
