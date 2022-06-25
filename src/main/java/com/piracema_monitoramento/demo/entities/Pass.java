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
@Table(name = "passes")
public class Pass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime registryDate;

    @ManyToOne
    @JoinColumn(name = "fk_fish")
    private Fish fish;

    @ManyToOne
    @JoinColumn(name = "fk_antenna")
    private Antenna antennaPass;

    private Long antennaIdentifier;

    private Long fishIdentifier;

    @PrePersist
    public void create() {
        registryDate = LocalDateTime.now();
    }
}
