package com.piracema_monitoramento.demo.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "antennas")
public class Antenna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime registryDate;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
    private LocalDateTime installationDate;

    private LocalDateTime uninstallDate;

    @OneToMany(mappedBy = "antenna", cascade={CascadeType.ALL})
    private List<Pass> passes;

    //VERIFICAR MELHOR MAPEAMENTO E SE INFLUENCIA
    @OneToMany(mappedBy = "antenna", cascade={CascadeType.ALL})
    private List<StatusAntenna> statusAntennas;

    @PrePersist
    public void create() {
        registryDate = LocalDateTime.now();
    }
}
