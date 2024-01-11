package it.unicam.cs.ids.TerraViva.Repository;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.AuthorizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationRepository<T extends AuthorizationEntity> extends JpaRepository<T, Long> {
}
