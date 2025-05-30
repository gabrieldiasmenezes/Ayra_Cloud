package br.com.fiap.Ayra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Ayra.model.SafeTip;

public interface SafeTipRepository extends JpaRepository<SafeTip, Long> {
    List<SafeTip> findByAlertId(Long alertId);
}
