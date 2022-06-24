package com.piracema_monitoramento.demo.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//DTO que retorna informações a cerca da localização da antena
public class AntennaLocationDTO {

    private Long id;

    private String latitude;

    private String longitude;

    private LocalDateTime installationDate;

    private LocalDateTime uninstallDate;

}
