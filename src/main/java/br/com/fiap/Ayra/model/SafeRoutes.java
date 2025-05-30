package br.com.fiap.Ayra.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "safe_routes")
public class SafeRoutes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_routes")
    private Long id;

    @Column(name = "routes", nullable = false)
    private String route;

    @ManyToOne
    @JoinColumn(name = "alert_id_alert", referencedColumnName = "id_alert", nullable = false)
    private Alert alert;
}