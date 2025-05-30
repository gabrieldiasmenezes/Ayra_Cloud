package br.com.fiap.Ayra.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "safe_tip")
public class SafeTip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tip")
    private Long id;

    @Column(name = "tip", nullable = false)
    private String tip;

    @ManyToOne
    @JoinColumn(name = "alert_id_alert", referencedColumnName = "id_alert", nullable = false)
    private Alert alert;
}