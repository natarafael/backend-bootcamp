package com.piracema_monitoramento.demo.entities.dtos;

import com.piracema_monitoramento.demo.entities.Pass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FishDTO {

    private Long id;

    private String pittag;

    private LocalDateTime registryDate;

    private String scientificName;

    private String commonName;

    private Integer totalLength;

    private Integer standardLength;

    private String captureLocation;

    private Integer releaseWeight;

    private LocalDateTime releaseDate;

    private String releaseLocation;

    private String dnaSample;

    private Boolean recapture = false;

    private List<PassNoFishDTO> passes;

}
