package br.com.fiap.Ayra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Ayra.model.SafeRoutes;

public interface SafeRoutesRepository extends JpaRepository<SafeRoutes, Long> {
    List<SafeRoutes> findByAlertIdAlert(Long alertId);
}
