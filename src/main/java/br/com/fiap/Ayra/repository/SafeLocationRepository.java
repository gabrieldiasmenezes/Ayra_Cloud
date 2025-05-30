package br.com.fiap.Ayra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Ayra.model.SafeLocation;

public interface SafeLocationRepository extends JpaRepository<SafeLocation, Long> {
    List<SafeLocation> findByAlertId(Long alertId);
}