package br.com.fiap.Ayra.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "map_marker")
public class MapMarker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_maker")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "intensity", nullable = false)
    private String intensity; // high, medium, low

    @Column(name = "radius", nullable = false)
    private Double radius;

    @ManyToOne
    @JoinColumn(name = "id_cor", referencedColumnName = "id_cor", nullable = false)
    private Coordinates coordinates;
}