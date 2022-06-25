package com.piracema_monitoramento.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "antenna_status")
public class StatusAntenna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime registryDate;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private LocalDateTime statusChangeDate;

    private String observations;

    @ManyToOne
    @JoinColumn(name = "fk_antenna")
    private Antenna statusAntenna;

    private Long antennaIdentifier;

    @PrePersist
    public void create() {
        registryDate = LocalDateTime.now();
    }

}
