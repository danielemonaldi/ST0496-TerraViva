package it.unicam.cs.ids.TerraViva.Repository;

import it.unicam.cs.ids.TerraViva.Models.POI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface POIRepository extends JpaRepository<POI, String> {

}