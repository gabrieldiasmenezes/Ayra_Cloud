package br.com.fiap.Ayra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.fiap.Ayra.model.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long>,JpaSpecificationExecutor<Alert> {
    // AlertRepository.java
    List<Alert> findByMapMarkerId(Long mapMarkerId);
}