package br.com.fiap.Ayra.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "alert")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alert")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "intensity", nullable = false)
    private String intensity; // high, medium, low

    @Column(name = "alert_datetime", nullable = false)
    private ZonedDateTime alertDatetime;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "radius", nullable = false)
    private Double radius;

    @ManyToOne
    @JoinColumn(name = "coordinates_id", referencedColumnName = "id_cor", nullable = false)
    private Coordinates coordinates;

    @OneToOne
    @JoinColumn(name = "id_map", referencedColumnName = "id_maker", nullable = false)
    private MapMarker mapMarker;
}