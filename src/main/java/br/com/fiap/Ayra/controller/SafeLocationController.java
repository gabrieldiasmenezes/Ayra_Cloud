package br.com.fiap.Ayra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;

import br.com.fiap.Ayra.model.SafeLocation;
import br.com.fiap.Ayra.repository.SafeLocationRepository;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/safe-location")
@Tag(name = "Locais Seguros", description = "Gerenciamento de locais seguros vinculados a um alerta")
public class SafeLocationController {

    @Autowired
    private SafeLocationRepository repository;

    // GET /safe-location - Listar todos os locais seguros
    @GetMapping
    public ResponseEntity<Page<SafeLocation>> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    // GET /safe-location/by-alert/{alertId}
    @GetMapping("/by-alert/{alertId}")
    public ResponseEntity<List<SafeLocation>> getByAlert(@PathVariable Long alertId) {
        return ResponseEntity.ok(repository.findByAlertIdAlert(alertId));
    }
}