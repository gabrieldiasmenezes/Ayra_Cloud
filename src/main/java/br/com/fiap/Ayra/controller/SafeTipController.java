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

import br.com.fiap.Ayra.model.SafeTip;
import br.com.fiap.Ayra.repository.SafeTipRepository;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/safe-tip")
@Tag(name = "Dicas de Segurança", description = "Gerenciamento de dicas vinculadas a um alerta")
public class SafeTipController {

    @Autowired
    private SafeTipRepository repository;

    // GET /safe-tip - Listar todas as dicas
    @GetMapping
    public ResponseEntity<Page<SafeTip>> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    // GET /safe-tip/by-alert/{alertId}
    @GetMapping("/by-alert/{alertId}")
    public ResponseEntity<List<SafeTip>> getByAlert(@PathVariable Long alertId) {
        return ResponseEntity.ok(repository.findByAlertIdAlert(alertId));
    }
}