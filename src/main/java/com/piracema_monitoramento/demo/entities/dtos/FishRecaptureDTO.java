package com.piracema_monitoramento.demo.entities.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class FishRecaptureDTO {

    private LocalDateTime registryDate;

    private Integer totalLength;

    private String captureLocation;

    private Integer releaseWeight;

    private Integer standardLength;

    private LocalDateTime releaseDate;

    private String releaseLocation;

    private String dnaSample;

    private Boolean recapture = true;

    private List<PassNoFishDTO> passes;
}
