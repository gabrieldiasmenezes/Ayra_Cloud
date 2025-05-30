package br.com.fiap.Ayra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.Ayra.model.Coordinates;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> ,JpaSpecificationExecutor<Coordinates> {
    List<Coordinates> findByLatitudeBetweenAndLongitudeBetween(Double minLat, Double maxLat, Double minLon, Double maxLon);
}
