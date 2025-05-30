package br.com.fiap.Ayra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.Ayra.model.MapMarker;

public interface MapMarkerRepository extends JpaRepository<MapMarker, Long>,JpaSpecificationExecutor<MapMarker> {
}