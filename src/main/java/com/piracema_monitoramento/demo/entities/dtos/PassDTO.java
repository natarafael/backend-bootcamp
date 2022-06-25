package com.piracema_monitoramento.demo.entities.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassDTO {

    private Long id;

    private LocalDateTime registryDate;

    private FishNoPassesDTO fish;

    private AntennaNoPassesDTO antenna;

    private Long antennaIdentifier;

    private Long fishIdentifier;
}
