package com.piracema_monitoramento.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fishes")
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pittag", nullable = false)
    private String pittag;

    private LocalDateTime registryDate;

    @Column(nullable = false)
    private String scientificName;

    private String commonName;

    @Column(nullable = false)
    private Integer totalLength;

    @Column(nullable = false)
    private String captureLocation;

    @Column(nullable = false)
    private Integer releaseWeight;

    @Column(nullable = false)
    private Integer standardLength;

    @Column(nullable = false)
    private LocalDateTime releaseDate;

    @Column(nullable = false)
    private String releaseLocation;

    @Column(nullable = false)
    private String dnaSample;

    @ColumnDefault("false")
    private Boolean recapture;

    @OneToMany(mappedBy = "fish", cascade={CascadeType.ALL})
    private List<Pass> passes;

    @PrePersist
    public void create() {
        registryDate = LocalDateTime.now();
    }

}
