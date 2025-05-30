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
import br.com.fiap.Ayra.model.SafeRoutes;
import br.com.fiap.Ayra.repository.SafeRoutesRepository;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/safe-routes")
@Tag(name = "Rotas Seguras", description = "Gerenciamento de rotas seguras vinculadas a um alerta")
public class SafeRoutesController {

    @Autowired
    private SafeRoutesRepository repository;

    // GET /safe-routes - Listar todas as rotas (opcionalmente paginado)
    @GetMapping
    public ResponseEntity<Page<SafeRoutes>> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    // GET /safe-routes/by-alert/{alertId} - Listar rotas seguras por alerta
    @GetMapping("/by-alert/{alertId}")
    public ResponseEntity<List<SafeRoutes>> getByAlert(@PathVariable Long alertId) {
        return ResponseEntity.ok(repository.findByAlertIdAlert(alertId));
    }
}