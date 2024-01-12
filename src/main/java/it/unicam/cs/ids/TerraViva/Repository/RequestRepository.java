package it.unicam.cs.ids.TerraViva.Repository;

import it.unicam.cs.ids.TerraViva.Models.Requests.MultiStatusRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository<T extends MultiStatusRequest> extends JpaRepository<T, Long> {
}
