package br.com.fiap.Ayra.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data // Gera getters, setters, equals, hashCode e toString
@Builder // Habilita o padrão Builder
@NoArgsConstructor // Gera um construtor sem argumentos
@AllArgsConstructor // Gera um construtor com todos os argumentos
@Table(name = "coordinates") // Nome da tabela no banco de dados
public class Coordinates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremento
    @Column(name = "id_cor", nullable = false)
    private Long id;

    @NotNull(message = "Latitude é obrigatória")
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @NotNull(message = "Longitude é obrigatória")
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @NotNull(message = "Data da coordenada é obrigatória")
    @Column(name = "date_coordinate", nullable = false)
    private LocalDate dateCoordinate; // Usamos LocalDate para representar datas
}