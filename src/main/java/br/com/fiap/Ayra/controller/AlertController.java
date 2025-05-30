package br.com.fiap.Ayra.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.Ayra.model.Alert;
import br.com.fiap.Ayra.repository.AlertRepository;
import br.com.fiap.Ayra.specification.AlertSpecification;

@RestController
@RequestMapping("/alert")
@Tag(name = "Alertas", description = "Endpoints para gerenciamento de alertas geográficos")
public class AlertController {

    public record AlertFilter(String intensity, Double latitude, Double longitude) {}

    @Autowired
    private AlertRepository repository;

    @GetMapping
    @Operation(
        summary = "Lista todos os alertas",
        description = "Retorna uma lista paginada de alertas cadastrados. Aceita filtros por intensidade ou proximidade geográfica.",
        parameters = {
            @Parameter(name = "intensity", description = "Filtrar por intensidade ('high', 'medium', 'low')", example = "high"),
            @Parameter(name = "latitude", description = "Latitude central para busca", example = "-23.567890"),
            @Parameter(name = "longitude", description = "Longitude central para busca", example = "-46.654321")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Listagem retornada com sucesso",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json"))
        }
    )
    public Page<Alert> getAll(
            AlertFilter filters,
            @PageableDefault(size = 10, sort = "id", direction = Direction.DESC) Pageable pageable) {
        return repository.findAll(AlertSpecification.withFilters(filters), pageable);
    }
}