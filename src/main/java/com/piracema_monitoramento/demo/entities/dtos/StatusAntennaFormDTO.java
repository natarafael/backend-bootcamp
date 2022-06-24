package com.piracema_monitoramento.demo.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusAntennaFormDTO {

    private LocalDateTime registryDate;

    private Boolean status;

    private LocalDateTime statusChangeDate;

    private String observations;

    private Long antennaID;
}
