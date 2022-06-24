package com.piracema_monitoramento.demo.entities.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FishSimpleDTO {

    private Long id;

    private String pittag;

    private String scientificName;

    private String commonName;

    private String captureLocation;

    private LocalDateTime releaseDate;

    private String releaseLocation;

    private String dnaSample;

    private Boolean recapture;

}
