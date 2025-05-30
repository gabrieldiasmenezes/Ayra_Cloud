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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.Ayra.model.Coordinates;
import br.com.fiap.Ayra.repository.CoordinatesRepository;
import br.com.fiap.Ayra.specification.CoordinatesSpecification;

@RestController
@RequestMapping("/coordinates")
@Tag(name = "Coordenadas", description = "Endpoints para gerenciamento de coordenadas geográficas")
public class CoordinatesController {

    public record CoordinatesFilter(Double latitude, Double longitude) {}

    @Autowired
    private CoordinatesRepository repository;

    @GetMapping
    @Operation(
        summary = "Lista todas as coordenadas",
        description = "Retorna uma página com coordenadas geográficas cadastradas, permitindo filtro opcional por latitude ou longitude.",
        parameters = {
            @Parameter(name = "latitude", description = "Filtrar por proximidade de latitude", example = "-23.567890"),
            @Parameter(name = "longitude", description = "Filtrar por proximidade de longitude", example = "-46.654321")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Listagem retornada com sucesso",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Coordinates.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos na requisição",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json"))
        }
    )
    public Page<Coordinates> getAll(CoordinatesFilter filters,
                                    @PageableDefault(size = 10, sort = "dateCoordinate", direction = Direction.DESC) Pageable pageable) {
        var specification = CoordinatesSpecification.withFilters(filters);
        return repository.findAll(specification, pageable);
    }
}