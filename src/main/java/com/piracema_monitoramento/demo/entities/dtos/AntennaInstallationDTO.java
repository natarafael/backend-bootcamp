package com.piracema_monitoramento.demo.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//DTO que retorna informações a cerca da instalação da antena
public class AntennaInstallationDTO {

    private Long id;

    private LocalDateTime installationDate;

    private LocalDateTime uninstallDate;
}
